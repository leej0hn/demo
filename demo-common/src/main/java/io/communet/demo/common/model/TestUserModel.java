package io.communet.demo.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/12/01
 * <p>Version: 1.0
 */
@Data
public class TestUserModel implements Serializable{
    private static final long serialVersionUID = 3881695489406782002L;
    private long id;
    private String name;
    private Integer age;
    private String subject;
    private Integer scope;
}
