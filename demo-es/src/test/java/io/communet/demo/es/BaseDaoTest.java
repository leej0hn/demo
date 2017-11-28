package io.communet.demo.es;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MHL on 16/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaseConfiguration.class)
//@Transactional
//@Rollback
@ActiveProfiles("test")
public abstract class BaseDaoTest {

}
