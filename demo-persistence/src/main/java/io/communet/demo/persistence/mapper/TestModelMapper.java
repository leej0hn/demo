package io.communet.demo.persistence.mapper;

import io.communet.demo.common.model.TestModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/9
 * <p>Version: 1.0
 */
@Mapper
public interface TestModelMapper {
    String ALL_COLUMN =
            "a.name AS 'name'," +
            "a.age AS 'age'";

    String TABLE_NAME = " test ";
    String TABLE_NAME_AS = TABLE_NAME + " AS a ";

    @Insert({
            "INSERT INTO",
            TABLE_NAME,
            "(name,age)",
            "VALUES",
            "( #{name},#{age})"
    })
    void insert(TestModel model);

}
