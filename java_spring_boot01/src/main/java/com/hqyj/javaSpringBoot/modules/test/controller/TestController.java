package com.hqyj.javaSpringBoot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("txt")
    @ResponseBody
    public String text(){

        return "这是一个字符串";
    }
}
