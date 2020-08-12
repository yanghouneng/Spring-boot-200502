package com.hqyj.springBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot.modules.common.vo.SearchVo;
import com.hqyj.springBoot.modules.test.entity.City;
import com.hqyj.springBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description CityController
 * @Author HymanHu
 * @Date 2020/8/11 14:12
 */
@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 127.0.0.1/api/cities/522 ---- get
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable  int countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }

    /**
     * 127.0.0.1/api/cities/522 --- post
     * {"currentPage":"1","pageSize":"5"}
     */
    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(
            @PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }
}
