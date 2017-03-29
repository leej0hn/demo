package io.communet.demo.web.interceptor;

import io.communet.demo.web.configuration.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/7/18
 * <p>Version: 1.0
 */
@Slf4j
@Component
public class IpInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER = "token_header";
    @Autowired
    private WebConfig config;

    public IpInterceptor(){
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if( config.getIps().equals("/*") || config.getIps().contains(request.getRemoteHost()) || (  request.getHeader(TOKEN_HEADER)!= null && request.getHeader(TOKEN_HEADER).equals(config.getTokenHeader()) ) ){
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
