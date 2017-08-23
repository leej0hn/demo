package io.communet.demo.common.model.neo4j;

import io.communet.demo.common.model.neo4j.base.Neo4jBaseModel;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/08/22
 * <p>Version: 1.0
 */
@Data
@NodeEntity
public class TestUserModel extends Neo4jBaseModel {
    private String name;
}
