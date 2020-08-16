package com.hqyj.springBoot.modules.test.controller;

import com.hqyj.springBoot.modules.test.entity.Country;
import com.hqyj.springBoot.modules.test.service.CountryServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description CountryController
 * @Author HymanHu
 * @Date 2020/8/11 14:01
 */
@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryServcie countryServcie;

    /**
     * 127.0.0.1/api/country/481 ---- get
     * @PathVariable ： 接收路径参数
     *  @RequestBody ：接收json参数
     *  consumes = "application/json" :进入控制器的数据类型
     *  produce="application/json"  ：控制器输出数据类型
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId) {
        return countryServcie.getCountryByCountryId(countryId);
    }

    /**
     * a127.0.0.1/api/country?countryName=Chin ---- get
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName) {
        return countryServcie.getCountryByCountryName(countryName);
    }

    /*
    * 127.0.0.1/api/redis/country/522
    * */
    //redis
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId){
        return countryServcie.mograteCountryByRedis(countryId);
    }
}
