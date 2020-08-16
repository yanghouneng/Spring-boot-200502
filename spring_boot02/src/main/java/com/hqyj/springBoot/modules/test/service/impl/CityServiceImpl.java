package com.hqyj.springBoot.modules.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot.modules.common.vo.Result;
import com.hqyj.springBoot.modules.common.vo.SearchVo;
import com.hqyj.springBoot.modules.test.dao.CityDao;
import com.hqyj.springBoot.modules.test.entity.City;
import com.hqyj.springBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Description CityServiceImpl
 * @Author HymanHu
 * @Date 2020/8/11 14:09
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesByCountryId(int countryId) {
//        return cityDao.getCitiesByCountryId(countryId);
        return Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(
                Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());

        return new PageInfo<City>(
                Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));

    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStats.SUCCESS.status,"insert success");
    }

    @Override
//    @Transactional(rollbackFor = ArithmeticException.class)
    @Transactional
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
//        int i = 1/0;
        return new Result<>(Result.ResultStats.SUCCESS.status,"update success");
    }

    @Override
    public Result<City> deleteCity(int cityId) {
        cityDao.deleteById(cityId);
        return new Result<City>(Result.ResultStats.SUCCESS.status,"delete success");
    }
}
