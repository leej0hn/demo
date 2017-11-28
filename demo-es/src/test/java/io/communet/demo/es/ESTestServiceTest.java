package io.communet.demo.es;

import io.communet.demo.common.model.es.WechatArticleEsModel;
import io.communet.demo.es.dao.TestEsDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/28
 * <p>Version: 1.0
 */
public class ESTestServiceTest extends BaseDaoTest {

    @Autowired
    TestEsDao testEsDao;

    @Test
    public void testRunning(){
        System.out.println("runnig");
    }

    @Test
    public void testFindAll(){
        Pageable pageable = new PageRequest(1,10);
        Iterable<WechatArticleEsModel> all = testEsDao.findAll(pageable);
        WechatArticleEsModel next = all.iterator().next();
        System.out.println(next);
    }


}
