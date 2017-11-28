package io.communet.demo.common.model.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Data
@Document(indexName = "test", type = "student")
public class TestUserEsModel implements Serializable {
    private static final long serialVersionUID = -8738138758341364319L;
    @Id
    private String id;
    private String name;
    private String age;
    private String subject;
    private String scope;
}
