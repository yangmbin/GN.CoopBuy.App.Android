package com.coopbuy.mall.eventbus;

import com.coopbuy.mall.eventbus.base.BaseEvent;

/**
 * @author csn
 * @date 2017/10/16 0016 19:34
 * @content
 */
public class RegisterEvent extends BaseEvent {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
