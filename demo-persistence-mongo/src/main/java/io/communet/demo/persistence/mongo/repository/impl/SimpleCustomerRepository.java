package io.communet.demo.persistence.mongo.repository.impl;

import io.communet.demo.common.model.mongo.base.MongoBaseModel;
import io.communet.demo.persistence.mongo.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/8/6
 * <p>Version: 1.0
 */
public class SimpleCustomerRepository<T, ID extends Serializable> extends QueryDslMongoRepository<T, ID> implements CustomerRepository<T, ID> {

    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, ID> entityInformation;

    public SimpleCustomerRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);

        this.mongoOperations = mongoOperations;
        this.entityInformation = entityInformation ;
    }

    @Override
    public void update(Query query, Update update) {
        if( query != null && update != null){
            this.mongoOperations.updateMulti(
                    query,
                    update.set("updatedAt",new Date()),
                    this.entityInformation.getCollectionName()
            );
        }
    }

    @Override
    public T findOne(Query query, String... ingoreFiles){
        ingore(query,ingoreFiles);
        return  this.mongoOperations.findOne(query,this.entityInformation.getJavaType());
    }

    @Override
    public List<T> findAll(Query query, String... ingoreFiles){
        ingore(query,ingoreFiles);
        return this.mongoOperations.find(query,this.entityInformation.getJavaType());
    }

    @Override
    public Page<T> findAll(Query query, Pageable pageable, String... ingoreFiles){
        ingore(query,ingoreFiles);
        Long count = this.mongoOperations.count(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
        List<T> list = this.mongoOperations.find(query.with(pageable),this.entityInformation.getJavaType());
        return new PageImpl<T>(list, pageable, count);
    }

    @Override
    public boolean exists(Query query){
        return !this.mongoOperations.find(query,this.entityInformation.getJavaType()).isEmpty();
    }

    @Override
    public <S extends T> S save(S entity) {
        if( entity instanceof MongoBaseModel){
            if( ((MongoBaseModel) entity).getUpdatedAt() == null ){
                ((MongoBaseModel) entity).setUpdatedAt(new Date());
            }
            if( ((MongoBaseModel) entity).getCreatedAt() == null ){
                ((MongoBaseModel) entity).setCreatedAt(new Date());
            }
        }
        return super.save(entity);
    }

    @Override
    public long count(Query query){
        return this.mongoOperations.count(query,this.entityInformation.getJavaType());
    }

    @Override
    public <T> AggregationResults<T> aggregate(TypedAggregation<?> aggregation, Class<T> outputType){
        return  mongoOperations.aggregate(aggregation, outputType);
    }

    private void ingore(Query query, String... ingoreFiles){
        if( ingoreFiles != null ){
            for (String ingoreFile : ingoreFiles) {
                query.fields().exclude(ingoreFile);
            }
        }
    }

}
