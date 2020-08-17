package com.hqyj.springBoot.config;

import com.hqyj.springBoot.filter.RequestParamFilter;
import com.hqyj.springBoot.interceptor.RequestViewInterceptor;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description WebMvcConfig
 * @Author HymanHu
 * @Date 2020/8/11 10:28
 */
@Configuration   //配置类注解
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestViewInterceptor requestViewInterceptor;

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

    @Bean  //注入过滤器注册到容器里面
    public FilterRegistrationBean<RequestParamFilter> register(){
        //filter注册器
        FilterRegistrationBean<RequestParamFilter> register =
                new FilterRegistrationBean<RequestParamFilter>();
        register.setFilter(new RequestParamFilter());
        return register;
    }

    //相当于拦截器的注册器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将引入的拦截器加入到注册器里面进行注册
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }
}
