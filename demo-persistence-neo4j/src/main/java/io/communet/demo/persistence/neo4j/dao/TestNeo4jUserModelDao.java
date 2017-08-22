package io.communet.demo.persistence.neo4j.dao;


import io.communet.demo.common.model.neo4j.TestUserModel;
import io.communet.demo.persistence.neo4j.repository.CustomerNeo4jRepository;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/8/22
 * <p>Version: 1.0
 */
public interface TestNeo4jUserModelDao extends CustomerNeo4jRepository<TestUserModel, Long> {

}
