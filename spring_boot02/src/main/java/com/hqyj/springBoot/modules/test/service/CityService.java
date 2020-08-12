package com.hqyj.springBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot.modules.common.vo.SearchVo;
import com.hqyj.springBoot.modules.test.entity.City;

import java.util.List;

/**
 * @Description CityService
 * @Author HymanHu
 * @Date 2020/8/11 14:09
 */
public interface CityService {

    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);
}
