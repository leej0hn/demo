package io.communet.demo.persistence.mongo.service;

import io.communet.demo.BaseDaoTest;
import io.communet.demo.common.model.mongo.TestMongoModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/9/1
 * <p>Version: 1.0
 */
public class TestMongoModelServiceTest extends BaseDaoTest {
    @Autowired
    TestMongoModelService service;

    @Test
    public void testSave(){
        TestMongoModel mongoModel = new TestMongoModel();
        mongoModel.setName("Lee");
        mongoModel.setAge(18);
        service.save(mongoModel);
    }

}
