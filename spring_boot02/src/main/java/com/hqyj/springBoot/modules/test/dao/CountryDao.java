package com.hqyj.springBoot.modules.test.dao;

import com.hqyj.springBoot.modules.test.entity.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description CountryDao
 * @Author HymanHu
 * @Date 2020/8/11 13:53
 * @Repository ：dao层的注解   @Result：封装结果集的注解  @Select查询的注解
 */
@Repository
@Mapper
public interface CountryDao {

    @Select("select * from m_country where country_id = #{countryId}")
    @Results(id = "countryResults", value = {
        @Result(column = "country_id", property = "countryId"),
        @Result(column = "country_id", property = "cities",
                javaType = List.class,
                many = @Many(select =
                        "com.hqyj.springBoot.modules.test.dao.CityDao.getCitiesByCountryId"))
    })
    Country getCountryByCountryId(int countryId);

    @Select("select * from m_country where country_name = #{countryName}")
    @ResultMap(value = "countryResults")
    Country getCountryByCountryName(String countryName);
}
