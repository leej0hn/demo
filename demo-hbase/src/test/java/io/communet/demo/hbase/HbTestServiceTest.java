package io.communet.demo.hbase;

import io.communet.demo.common.model.HbTestModel;
import io.communet.demo.hbase.service.HbTestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/08
 * <p>Version: 1.0
 */
public class HbTestServiceTest extends BaseDaoTest {

    @Autowired
    private HBaseClient hBaseClient;

    @Autowired
    private HbTestService service;

    @Test
    public void createTable() throws Exception{
        String[] family = new String[]{"f"};
        hBaseClient.createTable("test",family);
    }

    @Test
    public void testFindByService(){
        HbTestModel model = service.findByRowKey("LeeJohn");
        System.out.println(model);
    }

    @Test
    public void testSaveByService(){
        HbTestModel model = new HbTestModel();
        model.setAge("18");
        model.setName("LeeJohn");
        service.save(model);
    }


}
