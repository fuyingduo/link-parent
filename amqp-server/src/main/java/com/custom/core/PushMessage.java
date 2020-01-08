package com.custom.core;

import com.alibaba.fastjson.JSONObject;

/**
 * created by fuyd on 2019-11-06
 */
public class PushMessage {

    private Integer id;
    private String name;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJSONString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("message", message);
        return json.toJSONString();
    }
}
