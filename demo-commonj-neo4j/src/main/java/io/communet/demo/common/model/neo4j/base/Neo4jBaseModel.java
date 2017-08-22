package io.communet.demo.common.model.neo4j.base;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/8/4
 * <p>Version: 1.0
 */
@Data
public class Neo4jBaseModel {
    @GraphId
    private Long id;
}
