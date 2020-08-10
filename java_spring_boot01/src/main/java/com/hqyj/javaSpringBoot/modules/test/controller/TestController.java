package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ApplicationTest applicationTest;

    @Value("${server.port}")
    private String port;

    @Value("${com.name}")
    private String name;

    @Value("${com.age}")
    private int age;

    @Value("${com.desc}")
    private String desc;

    @Value("${com.random}")
    private String random;

    /*
    * 127.0.0.1:8085/test/log
    * */
    @GetMapping("log")
    @ResponseBody
    public String logTest(){
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test";
    }

    /*
    * 127.0.0.1:8085/test/config
    * */
    @GetMapping("config")
    @ResponseBody
    public String configTest(){
        StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(port).append("----")
                .append(name).append("----")
                .append(age).append("----")
                .append(desc).append("----")
                .append(random).append("---").append("<br>");

                stringBuffer.append(applicationTest.getPort()).append("-----")
                        .append(applicationTest.getName()).append("------")
                        .append(applicationTest.getAge()).append("-----")
                        .append(applicationTest.getDesc()).append("------")
                        .append(applicationTest.getRandom()).append("----").append("<br>");
                return stringBuffer.toString();
    }

    @RequestMapping("/txt")
    @ResponseBody
    public String text(){

        return "HelloWorld";
    }
}
