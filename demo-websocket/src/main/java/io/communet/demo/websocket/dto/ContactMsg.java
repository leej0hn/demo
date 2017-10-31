package io.communet.demo.websocket.dto;


import java.io.Serializable;
import java.util.List;


/**
 * <p>function: 泛粉丝推手机号实体
 * <p>User: LeeJohn
 * <p>Date: 2017/10/28
 * <p>Version: 1.0
 */
public class ContactMsg extends MsgBase implements Serializable{
    private static final long serialVersionUID = -615160665998607888L;
    private String batchId;//批次号
    private List<ContactModel> contactModels;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public List<ContactModel> getContactModels() {
        return contactModels;
    }

    public void setContactModels(List<ContactModel> contactModels) {
        this.contactModels = contactModels;
    }
}
