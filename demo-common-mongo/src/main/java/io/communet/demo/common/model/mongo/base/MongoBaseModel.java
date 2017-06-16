package io.communet.demo.common.model.mongo.base;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2016/8/4
 * <p>Version: 1.0
 */
@Data
public class MongoBaseModel {
    @Id
    private String id;
    @Indexed
    private Date createdAt;//创建日期
    @Indexed
    private Date updatedAt;//更新日期
}
