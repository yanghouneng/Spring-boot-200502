package com.hqyj.springBoot.modules.test.service;

import com.hqyj.springBoot.modules.test.entity.Country;

/**
 * @Description CountryServcie
 * @Author HymanHu
 * @Date 2020/8/11 13:59
 */
public interface CountryServcie {

    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    Country mograteCountryByRedis(int countryId);
}
