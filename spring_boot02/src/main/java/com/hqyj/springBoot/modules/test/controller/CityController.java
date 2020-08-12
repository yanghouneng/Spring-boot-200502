package com.hqyj.springBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBoot.modules.common.vo.Result;
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

    @Autowired  //自动装配
    private CityService cityService;

    /**
     * 127.0.0.1/api/cities/522 ---- get
     * @PathVariable： 接收路径参数注解
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable  int countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }

    /**
     * 127.0.0.1/api/cities/522 --- post
     * {"currentPage":"1","pageSize":"5"}
     *
     * @PathVariable ： 接收路径参数
     *  @RequestBody ：接收json参数
     *  consumes = "application/json" :进入控制器的数据类型
     *  produce="application/json"  ：控制器输出数据类型
     */
    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(
            @PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }

    /*
    * 127.0.0.1/api/cities--- post
      {"currentPage":"1","pageSize":"5","keyWord":"sh","orderBy":"city_name","order":"desc"}
    * */
    //多条件查询
    @PostMapping(value = "cities",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVo(searchVo);
    }
    /*
       127.0.0.1/api/insertCity
    * {"cityName":"test1","localCityName":"test2","countryId":"522"}
    * */
    //新增
    @PostMapping(value = "/insertCity",consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city){
        return cityService.insertCity(city);
    }

    /*
    * @PathVariable ： 接收路径参数
    *  127.0.0.1/api/updateCity
    *  {"cityId":"2257","cityName":"8888"}
    * */
    //修改
    @PutMapping(value = "/updateCity",consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /*
    * @PathVariable ： 接收路径参数
    * 127.0.0.1/api/deleteCity/2258
    *
    * */
    //删除
    @DeleteMapping(value = "/deleteCity/{cityId}")
    public Result<City> deleteCity(@PathVariable int cityId){
        return cityService.deleteCity(cityId);
    }
}
