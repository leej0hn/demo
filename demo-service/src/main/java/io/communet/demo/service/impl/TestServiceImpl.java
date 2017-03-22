package io.communet.demo.service.impl;

import io.communet.demo.common.vo.Response;
import io.communet.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/21
 * <p>Version: 1.0
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService{

    public TestServiceImpl(){
        log.info("TestServiceImpl constructor");
    }


    @Override
    public Response<String> test(String params1) {
        return Response.ok("Test OK");
    }

}
