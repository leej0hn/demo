package io.communet.demo.persistence.neo4j.service;


import io.communet.demo.common.model.neo4j.TestUserModel;
import io.communet.demo.persistence.neo4j.dao.TestNeo4jUserModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/08/22
 * <p>Version: 1.0
 */
@Service
public class TestNeo4jUserModelService {

    @Autowired
    private TestNeo4jUserModelDao testNeo4jUserModelDao;

    public void delete(Long Id){
        testNeo4jUserModelDao.delete(Id);
    }

    public TestUserModel save(TestUserModel model){
        return testNeo4jUserModelDao.save(model);
    }

    public Page<TestUserModel> findAll(Pageable pageable){
        return testNeo4jUserModelDao.findAll(pageable);
    }

}
