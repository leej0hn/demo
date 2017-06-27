package io.communet.demo.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * User: LeeJohn
 * Date: 14-4-17
 * Time: 上午10:35
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    @Before("execution(* io.communet.demo.web.controller..*(..))")
    public void logBefore(JoinPoint joinPoint){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            postData.append(entry.getKey()+" : ");
            for (String value : entry.getValue()) {
                postData.append(value +"; ");
            }
        }
        log.debug("=======request before===========\n [ContentType: {}/{}]-[method: {}]-[sessionId: {}] \n [url: {}] \n [params: {}]-[postData: {}].",
                request.getContentType(), request.getCharacterEncoding(),request.getMethod(),request.getSession().getId(),
                request.getRequestURL(),request.getQueryString(),postData.toString());
    }

    /**
     * 服务结束时调用
     * @param joinPoint
     * @param reponse
     */
    @AfterReturning(value = "execution(* io.communet.demo.web.controller..*(..)) or execution(* io.communet.demo.web.exception..*(..))",returning = "reponse")
    public void logAfterRunning(JoinPoint joinPoint, Object reponse){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            postData.append(entry.getKey()+" : ");
            for (String value : entry.getValue()) {
                postData.append(value +"; ");
            }
        }
        log.info("=======response after===========\n [ContentType: {}/{}]-[method: {}]-[sessionId: {}] \n [url: {}] \n [params: {}]-[postData: {}] \n [respons: {}].",
                request.getContentType(), request.getCharacterEncoding(),request.getMethod(),request.getSession().getId(),
                request.getRequestURL(),request.getQueryString(),postData.toString(),reponse == null?null:reponse.toString());
    }

}
