package io.communet.demo.service.aop;

import com.google.common.base.Throwables;
import io.communet.demo.common.exception.ServiceException;
import io.communet.demo.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/5/20.
 * <p>Version: 1.0
 */
@Component
@Aspect
@Slf4j
public class ServiceLogAspect {
    private static Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    public ServiceLogAspect(){
        log.info("ServiceLogAspect constructor");
    }

    /**
     * 调用启动服务
     * @param joinPoint
     */
    @Before("execution(* io.communet.demo.service.impl.*.*(..))")
    public void logBefore(JoinPoint joinPoint){
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();   //获得类名
        String methodName = joinPoint.getSignature().getName();   //获得方法名
        Object[] args = joinPoint.getArgs();  //获得参数列表
        StringBuilder argsBuilder = new StringBuilder();
        if(args != null && args.length > 0 ){
            for (int i = 0; i < args.length; i++) {
                argsBuilder.append("param["+i+"]:" + args[i]+"; ");
            }
        }
        log.debug("=======request before===========\n [{}]-[{}]-[{}].", clazzName,methodName,argsBuilder.toString());

    }

    @Around("execution(* io.communet.demo.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String errorMsg ;
        try {
            return proceedingJoinPoint.proceed();
        } catch (ServiceException se){
            logger.error(Throwables.getStackTraceAsString(se));
            errorMsg = se.getMessage();
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            errorMsg = "exception.fail";
        }
        logger.error("ExceptionHandler : "+errorMsg);
        return Response.fail(errorMsg);
    }

    /**
     * 服务结束时调用
     * @param joinPoint
     * @param reponse
     */
    @AfterReturning(value ="execution(* io.communet.demo.service.impl.*.*(..))",returning = "reponse")
    public void logAfterRunning(JoinPoint joinPoint, Object reponse){
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();   //获得类名
        String methodName = joinPoint.getSignature().getName();   //获得方法名
        Object[] args = joinPoint.getArgs();  //获得参数列表
        StringBuilder argsBuilder = new StringBuilder();
        if(args != null && args.length > 0 ){
            for (int i = 0; i < args.length; i++) {
                argsBuilder.append("param["+i+"]:" + args[i]+"; ");
            }
        }
        log.info("=======response after===========\n [{}]-[{}]-[{}]-[{}].", clazzName,methodName,reponse.toString(),argsBuilder.toString());
    }
}
