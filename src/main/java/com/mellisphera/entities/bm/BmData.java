package com.mellisphera.entities.bm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BmData implements Serializable {
    @JsonProperty("apiaries")
    private BmApiary[] apiaries;

    @JsonProperty("user")
    private Object user;

    @JsonProperty("userId")
    private String userId;

    public BmData(BmApiary[] apiaries) {
        this.apiaries = apiaries;
    }

    public BmApiary[] getApiaries() {
        return apiaries;
    }

    public void setApiaries(BmApiary[] apiaries) {
        this.apiaries = apiaries;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
