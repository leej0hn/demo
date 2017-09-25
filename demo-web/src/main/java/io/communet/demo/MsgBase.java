package io.communet.demo;

import java.io.Serializable;


/**
 * <p>function: 微信netty通信基础类
 * <p>User: LeeJohn
 * <p>Date: 2017/08/04
 * <p>Version: 1.0
 */
public class MsgBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clientId ;//netty客户端ID
    private String clientWxId;//客户端微信ID
    private String apiCode ; //接口编码

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientWxId() {
        return clientWxId;
    }

    public void setClientWxId(String clientWxId) {
        this.clientWxId = clientWxId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }
}
