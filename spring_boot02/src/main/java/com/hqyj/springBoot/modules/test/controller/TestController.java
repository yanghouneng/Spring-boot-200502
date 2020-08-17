package com.hqyj.springBoot.modules.test.controller;

import com.hqyj.springBoot.modules.test.entity.City;
import com.hqyj.springBoot.modules.test.entity.Country;
import com.hqyj.springBoot.modules.test.service.CityService;
import com.hqyj.springBoot.modules.test.service.CountryServcie;
import com.hqyj.springBoot.modules.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TestController
 * @Author HymanHu
 * @Date 2020/8/10 10:39
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryServcie countryServcie;

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    @Autowired
    private ApplicationTest applicationTest;

    /*
    * 127.0.0.1/test/index
    * */
   @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap){
       int countryId = 522;
       List<City> cities = cityService.getCitiesByCountryId(countryId);
       cities = cities.stream().limit(10).collect(Collectors.toList());
       Country country = countryServcie.getCountryByCountryId(countryId);

       modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
       modelMap.addAttribute("checked", true);
       modelMap.addAttribute("currentNumber", 99);
       modelMap.addAttribute("changeType", "checkbox");
       modelMap.addAttribute("baiduUrl", "/test/log");
       modelMap.addAttribute("city", cities.get(0));
       modelMap.addAttribute("shopLogo",
               "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
       modelMap.addAttribute("shopLogo",
               "/upload/111.png");
       modelMap.addAttribute("country", country);
       modelMap.addAttribute("cities", cities);
       modelMap.addAttribute("updateCityUri", "/api/city");
       modelMap.addAttribute("template", "test/index");
       //返回外部碎片的组装器
        return "index";
    }

    /*
    * 127.0.0.1/test/index2
    * */
    @GetMapping("index2")
    public String testIndex2Page(ModelMap modelMap){
      modelMap.addAttribute("template","test/index2");
       return "index";
    }

    /**
     * 127.0.0.1:8085/test/logTest ---- get
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test11111";
    }

    /**
     * 127.0.0.1:8085/test/config ---- get
     */
    @GetMapping("/config")
    @ResponseBody
    public String configTest() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("----")
            .append(name).append("----")
            .append(age).append("----")
            .append(desc).append("----")
            .append(random).append("----").append("<br>");
        sb.append(applicationTest.getPort()).append("----")
            .append(applicationTest.getName()).append("----")
            .append(applicationTest.getAge()).append("----")
            .append(applicationTest.getDesc()).append("----")
            .append(applicationTest.getRandom()).append("----").append("<br>");

        return sb.toString();

    }

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request, @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc."+paramValue+"=="+paramValue2;
    }

}
