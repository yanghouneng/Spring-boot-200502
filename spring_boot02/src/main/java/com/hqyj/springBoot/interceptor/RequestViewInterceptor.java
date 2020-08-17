package com.hqyj.springBoot.interceptor;

import javafx.scene.shape.PathElement;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestViewInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestViewInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("==========preHandle interceptor==========");
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("==========post interceptor==========");
        if (modelAndView == null || modelAndView.getViewName().startsWith("redirect")){
            return;
        }

        //获取path路径
        String path = request.getServletPath();
        //获取ModelMap中的值
        String template = (String) modelAndView.getModelMap().get("template");
        //如果该值不为空
        if (StringUtils.isBlank(template)){
            //if路径以/开头
            if (path.startsWith("/")){
                path.substring(1);
            }
            modelAndView.getModelMap().addAttribute("template",path.toLowerCase());
        }
        HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("==========after interceptor==========");
        HandlerInterceptor.super.preHandle(request,response,handler);
    }
}
