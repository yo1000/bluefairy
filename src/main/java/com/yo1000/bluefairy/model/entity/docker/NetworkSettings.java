package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/03/17.
 */
public class NetworkSettings {
    @JsonProperty("Bridge")
    private String bridge;
    @JsonProperty("Gateway")
    private String gateway;
    @JsonProperty("GlobalIPv6Address")
    private String globalIPv6Address;
    @JsonProperty("GlobalIPv6PrefixLen")
    private Integer globalIPv6PrefixLen;
    @JsonProperty("IPAddress")
    private String ipAddress;
    @JsonProperty("IPPrefixLen")
    private Integer ipPrefixLen;
    @JsonProperty("IPv6Gateway")
    private String ipv6Gateway;
    @JsonProperty("LinkLocalIPv6Address")
    private String linkLocalIPv6Address;
    @JsonProperty("LinkLocalIPv6PrefixLen")
    private Integer linkLocalIPv6PrefixLen;
    @JsonProperty("MacAddress")
    private String macAddress;
    @JsonProperty("PortMapping")
    private String portMapping;
    @JsonProperty("Ports")
    private HashMap<String, PortBinding[]> ports;

    public String getBridge() {
        return bridge;
    }

    public void setBridge(String bridge) {
        this.bridge = bridge;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getGlobalIPv6Address() {
        return globalIPv6Address;
    }

    public void setGlobalIPv6Address(String globalIPv6Address) {
        this.globalIPv6Address = globalIPv6Address;
    }

    public Integer getGlobalIPv6PrefixLen() {
        return globalIPv6PrefixLen;
    }

    public void setGlobalIPv6PrefixLen(Integer globalIPv6PrefixLen) {
        this.globalIPv6PrefixLen = globalIPv6PrefixLen;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIpPrefixLen() {
        return ipPrefixLen;
    }

    public void setIpPrefixLen(Integer ipPrefixLen) {
        this.ipPrefixLen = ipPrefixLen;
    }

    public String getIpv6Gateway() {
        return ipv6Gateway;
    }

    public void setIpv6Gateway(String ipv6Gateway) {
        this.ipv6Gateway = ipv6Gateway;
    }

    public String getLinkLocalIPv6Address() {
        return linkLocalIPv6Address;
    }

    public void setLinkLocalIPv6Address(String linkLocalIPv6Address) {
        this.linkLocalIPv6Address = linkLocalIPv6Address;
    }

    public Integer getLinkLocalIPv6PrefixLen() {
        return linkLocalIPv6PrefixLen;
    }

    public void setLinkLocalIPv6PrefixLen(Integer linkLocalIPv6PrefixLen) {
        this.linkLocalIPv6PrefixLen = linkLocalIPv6PrefixLen;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getPortMapping() {
        return portMapping;
    }

    public void setPortMapping(String portMapping) {
        this.portMapping = portMapping;
    }

    public HashMap<String, PortBinding[]> getPorts() {
        return ports;
    }

    public void setPorts(HashMap<String, PortBinding[]> ports) {
        this.ports = ports;
    }
}
