package io.communet.demo.persistence.mongo.dao;

import io.communet.demo.common.model.mongo.TestMongoModel;
import io.communet.demo.persistence.mongo.repository.CustomerRepository;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/8/4
 * <p>Version: 1.0
 */
public interface TestMongoModelDao extends CustomerRepository<TestMongoModel, String> {

}
