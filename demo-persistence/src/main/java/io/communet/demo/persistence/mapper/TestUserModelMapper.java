package io.communet.demo.persistence.mapper;

import io.communet.demo.common.model.TestUserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/12/01
 * <p>Version: 1.0
 */
@Mapper
public interface TestUserModelMapper {
    String ALL_COLUMN =
            " a.id AS id " +
            " a.name AS name " +
            " a.age AS age " +
            " a.subject AS subject " +
            " a.scope AS scope ";

    String TABLE_NAME = " testuser ";
    String TABLE_NAME_AS = TABLE_NAME + " AS a ";

    /**
     * @Options 返回自增主键设置
     * @param model
     */
    @Insert({
            "INSERT INTO",
            TABLE_NAME,
            "(name,age,subject,scope)",
            "VALUES",
            "( #{name},#{age},#{subject},#{scope})"
    })
    @Options(keyProperty = "id",useGeneratedKeys = true )
    void insert(TestUserModel model);

    /**
     * 动态拼接语句需要增加<script>标签
     * EXPLAIN + sql语句，检测查询语句是否命中索引
     */
    @Select({
            "<script>",
            "SELECT a.name AS name , SUM(a.scope) AS scope , a.subject AS subject",
            "FROM  " + TABLE_NAME_AS ,
            "WHERE 1=1 " ,
            "AND  a.name IN ",
            "<foreach item='name' index='index' collection='names' open='(' separator=',' close=')'> #{name} </foreach> " ,
            "GROUP BY a.subject",
            "<choose>",
            "<when test = 'type == 0 '> ORDER BY scope ASC </when>",
            "<otherwise> ORDER BY scope DESC </otherwise>",
            "</choose>",
            "</script>"
    })
    @ResultType(TestUserModel.class)
    List<TestUserModel> userGroupBySubject(
            @Param("names")String[] names,
            @Param("type")int type
    );


}
