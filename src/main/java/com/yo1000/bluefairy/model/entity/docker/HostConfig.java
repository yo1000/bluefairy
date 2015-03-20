package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/03/16.
 */
public class HostConfig {
    public static class RestartPolicy {
        @JsonProperty("Name")
        private String name;
        @JsonProperty("MaximumRetryCount")
        private Integer maximumRetryCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMaximumRetryCount() {
            return maximumRetryCount;
        }

        public void setMaximumRetryCount(Integer maximumRetryCount) {
            this.maximumRetryCount = maximumRetryCount;
        }
    }

    @JsonProperty("Binds")
    private String[] binds;
    @JsonProperty("IpcMode")
    private String ipcMode;
    @JsonProperty("PidMode")
    private String pidMode;
    @JsonProperty("Links")
    private String[] links;
    @JsonProperty("LxcConf")
    private HashMap<String, String>[] lxcConf;
    @JsonProperty("PortBindings")
    private HashMap<String, PortBinding[]> portBindings;
    @JsonProperty("PublishAllPorts")
    private Boolean publishAllPorts;
    @JsonProperty("Privileged")
    private Boolean privileged;
    @JsonProperty("ReadonlyRootfs")
    private Boolean readonlyRootfs;
    @JsonProperty("Dns")
    private String[] dns;
    @JsonProperty("DnsSearch")
    private String[] dnsSearch;
    @JsonProperty("ExtraHosts")
    private String[] extraHosts;
    @JsonProperty("VolumesFrom")
    private String[] volumesFrom;
    @JsonProperty("CapAdd")
    private String[] capAdd;
    @JsonProperty("CapDrop")
    private String[] capDrop;
    @JsonProperty("ContainerIDFile")
    private String containerIDFile;
    @JsonProperty("RestartPolicy")
    private RestartPolicy restartPolicy;
    @JsonProperty("NetworkMode")
    private String networkMode;
    @JsonProperty("SecurityOpt")
    private String securityOpt;
    @JsonProperty("Devices")
    private String[] devices;

    public String[] getBinds() {
        return binds;
    }

    public void setBinds(String[] binds) {
        this.binds = binds;
    }

    public String getIpcMode() {
        return ipcMode;
    }

    public void setIpcMode(String ipcMode) {
        this.ipcMode = ipcMode;
    }

    public String getPidMode() {
        return pidMode;
    }

    public void setPidMode(String pidMode) {
        this.pidMode = pidMode;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }

    public HashMap<String, String>[] getLxcConf() {
        return lxcConf;
    }

    public void setLxcConf(HashMap<String, String>[] lxcConf) {
        this.lxcConf = lxcConf;
    }

    public HashMap<String, PortBinding[]> getPortBindings() {
        return portBindings;
    }

    public void setPortBindings(HashMap<String, PortBinding[]> portBindings) {
        this.portBindings = portBindings;
    }

    public Boolean getPublishAllPorts() {
        return publishAllPorts;
    }

    public void setPublishAllPorts(Boolean publishAllPorts) {
        this.publishAllPorts = publishAllPorts;
    }

    public Boolean getPrivileged() {
        return privileged;
    }

    public void setPrivileged(Boolean privileged) {
        this.privileged = privileged;
    }

    public Boolean getReadonlyRootfs() {
        return readonlyRootfs;
    }

    public void setReadonlyRootfs(Boolean readonlyRootfs) {
        this.readonlyRootfs = readonlyRootfs;
    }

    public String[] getDns() {
        return dns;
    }

    public void setDns(String[] dns) {
        this.dns = dns;
    }

    public String[] getDnsSearch() {
        return dnsSearch;
    }

    public void setDnsSearch(String[] dnsSearch) {
        this.dnsSearch = dnsSearch;
    }

    public String[] getExtraHosts() {
        return extraHosts;
    }

    public void setExtraHosts(String[] extraHosts) {
        this.extraHosts = extraHosts;
    }

    public String[] getVolumesFrom() {
        return volumesFrom;
    }

    public void setVolumesFrom(String[] volumesFrom) {
        this.volumesFrom = volumesFrom;
    }

    public String[] getCapAdd() {
        return capAdd;
    }

    public void setCapAdd(String[] capAdd) {
        this.capAdd = capAdd;
    }

    public String[] getCapDrop() {
        return capDrop;
    }

    public void setCapDrop(String[] capDrop) {
        this.capDrop = capDrop;
    }

    public String getContainerIDFile() {
        return containerIDFile;
    }

    public void setContainerIDFile(String containerIDFile) {
        this.containerIDFile = containerIDFile;
    }

    public RestartPolicy getRestartPolicy() {
        return restartPolicy;
    }

    public void setRestartPolicy(RestartPolicy restartPolicy) {
        this.restartPolicy = restartPolicy;
    }

    public String getNetworkMode() {
        return networkMode;
    }

    public void setNetworkMode(String networkMode) {
        this.networkMode = networkMode;
    }

    public String getSecurityOpt() {
        return securityOpt;
    }

    public void setSecurityOpt(String securityOpt) {
        this.securityOpt = securityOpt;
    }

    public String[] getDevices() {
        return devices;
    }

    public void setDevices(String[] devices) {
        this.devices = devices;
    }
}
