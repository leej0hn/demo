package io.communet.demo.persistence.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/8/6
 * <p>Version: 1.0
 */
@NoRepositoryBean
public interface CustomerRepository<T, ID extends Serializable> extends MongoRepository<T, ID>, QueryDslPredicateExecutor<T> {

    void update(Query query, Update update);

    T findOne(Query query, String... ingoreFiles);

    List<T> findAll(Query query, String... ingoreFiles);

    Page<T> findAll(Query query, Pageable pageable, String... ingoreFiles);

    boolean exists(Query query);

    long count(Query query);

    <T> AggregationResults<T> aggregate(TypedAggregation<?> aggregation, Class<T> outputType);
}
