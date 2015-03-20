package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yoichi.kikuchi on 15/03/17.
 */
public class Port {
    @JsonProperty("IP")
    private String ip;
    @JsonProperty("PrivatePort")
    private Integer privatePort;
    @JsonProperty("PublicPort")
    private Integer publicPort;
    @JsonProperty("Type")
    private String type;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(Integer privatePort) {
        this.privatePort = privatePort;
    }

    public Integer getPublicPort() {
        return publicPort;
    }

    public void setPublicPort(Integer publicPort) {
        this.publicPort = publicPort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
