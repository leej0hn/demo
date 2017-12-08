package io.communet.demo.common.model.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;


@Data
@Document(indexName = "top", type = "fans")
public class TestFansEsModel implements Serializable {
    private static final long serialVersionUID = 7782145140610206240L;
    @Id
    private String id;
    private String city;
    /**
     * 公司Id
     */
    private String compId;
    private List<TestAcct> acctList;

}
