package io.communet.demo.service.impl;

import io.communet.demo.common.model.mongo.TestMongoModel;
import io.communet.demo.common.vo.Response;
import io.communet.demo.persistence.mongo.service.TestMongoModelService;
import io.communet.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    //注意：1.5.3 spring boot 后，mongodb+dubbo整合，会涉及到bean依赖循环的异常，需要加lazy注解
    @Autowired
    @Lazy
    private TestMongoModelService testMongoModelService;

    public TestServiceImpl(){
        log.info("TestServiceImpl constructor");
    }


    @Override
    public Response<String> test(String params1) {
        TestMongoModel mongoModel = new TestMongoModel();
        mongoModel.setAge(1);
        mongoModel.setName("name");
        testMongoModelService.save(mongoModel);
        return Response.ok();
    }

}
