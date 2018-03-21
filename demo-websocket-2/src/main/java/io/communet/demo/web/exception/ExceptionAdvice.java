package io.communet.demo.web.exception;

import io.communet.demo.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/15
 * <p>Version: 1.0
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler( Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response exception(Exception exception) {
        log.error(exception.getMessage());
        return Response.fail(exception.getMessage());
    }
}
