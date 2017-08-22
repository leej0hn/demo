package io.communet.demo.persistence.neo4j.repository.impl;


import io.communet.demo.persistence.neo4j.repository.CustomerNeo4jRepository;
import org.neo4j.ogm.session.Session;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;

import java.io.Serializable;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/08/22
 * <p>Version: 1.0
 */
public class SimpleCustomerNeo4jRepository<T, ID extends Serializable> extends SimpleNeo4jRepository<T, ID> implements CustomerNeo4jRepository<T, ID> {

    private final Class<T> domainClass;
    private final Session session;

    public SimpleCustomerNeo4jRepository(Class<T> domainClass, Session session) {
        super(domainClass, session);
        this.domainClass = domainClass;
        this.session = session;
    }


}
