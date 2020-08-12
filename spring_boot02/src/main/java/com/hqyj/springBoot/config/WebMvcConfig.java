package com.hqyj.springBoot.config;

import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description WebMvcConfig
 * @Author HymanHu
 * @Date 2020/8/11 10:28
 */
@Configuration   //配置类注解
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig {

    @Value("${server.http.port}")  //获取配置文件的值
    private int httpPort;

    @Bean  //注册bean注解
    public Connector connector() {
        Connector connector = new Connector();
        connector.setPort(httpPort);
        connector.setScheme("http");
        return connector;
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector());
        return tomcat;
    }
}
