package io.communet.demo.websocket.dto;

/**
 * <p>function: 通讯录实体
 * <p>User: LeeJohn
 * <p>Date: 2017/09/07
 * <p>Version: 1.0
 */
public class ContactModel {

    private Long phone;
    private String name;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
