package com.hqyj.springBoot.modules.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description ApplicationTest
 * @Author HymanHu
 * @Date 2020/8/10 15:51
 */
@Component   //扫描组件
@PropertySource("classpath:config/applicationTest.properties")   //绑定配置文件
@ConfigurationProperties(prefix = "com.qq")  //设置配置类属性
public class ApplicationTest {
    private int port;
    private String name;
    private int age;
    private String desc;
    private String random;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
