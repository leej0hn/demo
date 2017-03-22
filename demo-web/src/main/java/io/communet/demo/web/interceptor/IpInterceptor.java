package io.communet.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
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

    @Value("${web.ips:}")
    private String ips;

    public IpInterceptor(){
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if( ips.equals("/*") || ips.contains(request.getRemoteHost()) ){
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
