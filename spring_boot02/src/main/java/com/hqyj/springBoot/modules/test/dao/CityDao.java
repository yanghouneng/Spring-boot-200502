package com.hqyj.springBoot.modules.test.dao;

import com.hqyj.springBoot.modules.common.vo.SearchVo;
import com.hqyj.springBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
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


    @Select("<script>" +
            "select * from m_city "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by city_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    /*
    * @Options（）可选项   keyColumn对应表的列，对应的属性
    * */
    //新增
    @Insert("insert into m_city (city_name,local_city_name,country_id,date_modified)"+"value (#{cityName},#{localCityName},#{countryId},#{dateCreated})")
    @Options(useGeneratedKeys = true,keyColumn = "city_id",keyProperty = "cityId")
    void insertCity(City city);

    //修改
    @Update("update m_city set city_name=#{cityName} where city_id = #{cityId}")
    void updateCity(City city);

    //删除
    @Delete("delete from m_city where city_id=#{cityId}")
    void deleteById(int cityId);
}
