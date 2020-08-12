package com.hqyj.springBoot.modules.test.dao;

import com.hqyj.springBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description CityDao
 * @Author HymanHu
 * @Date 2020/8/11 14:08
 */
@Repository    //dao层注解
@Mapper
public interface CityDao {

    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCitiesByCountryId(int countryId);
}
