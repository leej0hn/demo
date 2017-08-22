package io.communet.demo.persistence.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/8/22
 * <p>Version: 1.0
 */
@NoRepositoryBean
public interface CustomerNeo4jRepository<T, ID extends Serializable> extends Neo4jRepository<T, ID> {

}
