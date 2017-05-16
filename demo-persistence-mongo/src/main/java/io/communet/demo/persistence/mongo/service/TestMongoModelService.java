package io.communet.demo.persistence.mongo.service;

import com.querydsl.core.types.Predicate;
import io.communet.demo.common.model.mongo.TestMongoModel;
import io.communet.demo.persistence.mongo.dao.TestMongoModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/9/1
 * <p>Version: 1.0
 */
@Service
public class TestMongoModelService {
    @Autowired
    private TestMongoModelDao testMongoModelDao;

    public void delete(String Id){
        testMongoModelDao.delete(Id);
    }

    public TestMongoModel save(TestMongoModel model){
        return testMongoModelDao.save(model);
    }

    public Page<TestMongoModel> findAll(Pageable pageable){
        return testMongoModelDao.findAll(pageable);
    }

    /**
     * 分页查询
     */
    public Page<TestMongoModel> findAll(Query query, Pageable pageable, String... ingoreFiles){
        return testMongoModelDao.findAll(query,pageable,ingoreFiles);
    }

    public Boolean exists(Predicate predicate){
        return testMongoModelDao.exists(predicate);
    }

    public void update(Query query, Update update){
        testMongoModelDao.update(query,update);
    }

    public TestMongoModel get(String Id){
        return testMongoModelDao.findOne(Id);
    }

    public Boolean exists(String id){
        return testMongoModelDao.exists(id);
    }

    public List<TestMongoModel> findAll(){
        return testMongoModelDao.findAll();
    }

    public Long count(Query query){
        return testMongoModelDao.count(query);
    }
}
