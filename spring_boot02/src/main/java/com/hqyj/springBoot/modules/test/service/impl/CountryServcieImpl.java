package com.hqyj.springBoot.modules.test.service.impl;

import com.hqyj.springBoot.modules.test.dao.CountryDao;
import com.hqyj.springBoot.modules.test.entity.Country;
import com.hqyj.springBoot.modules.test.service.CountryServcie;
import com.hqyj.springBoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description CountryServcieImpl
 * @Author HymanHu
 * @Date 2020/8/11 13:59
 */
@Service
public class CountryServcieImpl implements CountryServcie {

    @Autowired
    private CountryDao countryDao;

    @Autowired
//    private RedisTemplate redisTemplate;
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    /*
    * 将数据查询出来存储到redis中再返回给接口
    * */
    @Override
    public Country mograteCountryByRedis(int countryId) {
        //从数据库里将country数据查询出来
        Country country= countryDao.getCountryByCountryId(countryId);
        String countryKey = String.format("country%d",countryId);
        //将查询出来的数据存储存到redis中
//        redisTemplate.opsForValue().set(countryKey,country);
        redisUtils.set(countryKey,country);
        //取出来countryKey返回
//        return (Country) redisTemplate.opsForValue().get(countryKey);
        return (Country)redisUtils.get(countryKey);
    }
}
