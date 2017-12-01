package io.communet.demo.persistence.mapper;

import io.communet.demo.common.model.TestUserModel;
import io.communet.demo.persistence.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/9
 * <p>Version: 1.0
 */
public class TestUserModelMapperTest extends BaseDaoTest {
    @Autowired
    TestUserModelMapper mapper;

    @Test
    public void test() throws Exception{
        TestUserModel model = new TestUserModel();
        model.setAge(18);
        model.setName("JackQ");
        model.setScope(99);
        model.setSubject("math");
        mapper.insert(model);
        System.out.println("id : " + model.getId());
    }

    @Test
    public void testUserGroupBySubject() throws Exception{
        String[] names = new String[]{"JackQ","LeeO","Cheer","Tom","PPT"};
        List<TestUserModel> testUserModels = mapper.userGroupBySubject(names, 1);
        System.out.println(testUserModels);
    }
}
