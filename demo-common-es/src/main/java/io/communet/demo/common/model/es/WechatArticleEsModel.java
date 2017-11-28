package io.communet.demo.common.model.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Data
@Document(indexName = "g7", type = "wx_article")
public class WechatArticleEsModel implements Serializable {
    private static final long serialVersionUID = -8738138758341364319L;

    @Id
    private String sn;

    private String accountId;

    private String createTime;

    private String alias;

    private String title;

    private String words;

    private String content;
}
