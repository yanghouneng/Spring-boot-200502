package com.hqyj.springBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "requestParamFilter",urlPatterns = "/**")
public class RequestParamFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       LOGGER.debug("======init Filter=====");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       LOGGER.debug("=========Request param Filter==========");
        HttpServletRequest httpServletRequest =(HttpServletRequest)servletRequest;
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest){
            @Override
            public String getParameter(String name) {
                String value=httpServletRequest.getParameter(name);
                if (!StringUtils.isBlank(value)){
                    return value.replaceAll("fuck","****");

                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values=httpServletRequest.getParameterValues(name);
                if (values.length>0 &&values!=null){
                    for (int i = 0; i < values.length; i++) {
                        if (values[i].equals("fuck")){
                            values[i]="****";
                        }
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        //TOOD
        filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {
       LOGGER.debug("=======destroy param filter========");
    }
}
