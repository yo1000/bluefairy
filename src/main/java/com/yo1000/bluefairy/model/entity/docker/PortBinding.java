package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yoichi.kikuchi on 15/03/18.
 */
public class PortBinding {
    @JsonProperty("HostIp")
    private String hostIp;
    @JsonProperty("HostPort")
    private String hostPort;

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }
}
