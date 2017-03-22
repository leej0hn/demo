package io.communet.demo.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/9
 * <p>Version: 1.0
 */
@Data
public class TestModel implements Serializable{
    private static final long serialVersionUID = 954117211539049255L;

    private String name;
    private int age;
}
