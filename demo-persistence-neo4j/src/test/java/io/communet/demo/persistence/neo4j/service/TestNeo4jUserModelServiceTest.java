package io.communet.demo.persistence.neo4j.service;

import io.communet.demo.BaseDaoTest;
import io.communet.demo.common.model.neo4j.TestUserModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/08/22
 * <p>Version: 1.0
 */
public class TestNeo4jUserModelServiceTest extends BaseDaoTest {
    @Autowired
    TestNeo4jUserModelService service;

    @Test
    public void testSave(){
        TestUserModel model = new TestUserModel();
//        model.setId(1L);
        model.setName("LeeQQQQ");
        TestUserModel save = service.save(model);
        System.out.println(save);
    }

    @Test
    public void testFind(){
        Pageable pageable= new PageRequest(0, 100);
        Page<TestUserModel> all = service.findAll(pageable);
        System.out.println(all.getContent());
    }

    @Test
    public void delete(){
        service.delete(0L);
    }

}
