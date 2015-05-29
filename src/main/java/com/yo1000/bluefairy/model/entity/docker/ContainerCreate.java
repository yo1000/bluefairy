package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/03/15.
 */
public class ContainerCreate {
    @JsonProperty("Hostname")
    private String hostname;
    @JsonProperty("Domainname")
    private String domainname;
    @JsonProperty("User")
    private String user;
    @JsonProperty("Memory")
    private Long memory;
    @JsonProperty("MemorySwap")
    private Long memorySwap;
    @JsonProperty("CpuShares")
    private Integer cpuShares;
    @JsonProperty("Cpuset")
    private String cpuset;
    @JsonProperty("AttachStdin")
    private Boolean attachStdin;
    @JsonProperty("AttachStdout")
    private Boolean attachStdout;
    @JsonProperty("AttachStderr")
    private Boolean attachStderr;
    @JsonProperty("Tty")
    private Boolean tty;
    @JsonProperty("OpenStdin")
    private Boolean openStdin;
    @JsonProperty("StdinOnce")
    private Boolean stdinOnce;
    @JsonProperty("Env")
    private String[] env;
    @JsonProperty("Cmd")
    private String[] cmd;
    @JsonProperty("Entrypoint")
    private String[] entrypoint;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("Volumes")
    private HashMap<String, HashMap<String, String>> volumes;
    @JsonProperty("WorkingDir")
    private String workingDir;
    @JsonProperty("NetworkDisabled")
    private Boolean networkDisabled;
    @JsonProperty("MacAddress")
    private String macAddress;
    @JsonProperty("ExposedPorts")
    private HashMap<String, HashMap<String, String>> exposedPorts;
    @JsonProperty("SecurityOpts")
    private String[] securityOpts;
    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getMemorySwap() {
        return memorySwap;
    }

    public void setMemorySwap(Long memorySwap) {
        this.memorySwap = memorySwap;
    }

    public Integer getCpuShares() {
        return cpuShares;
    }

    public void setCpuShares(Integer cpuShares) {
        this.cpuShares = cpuShares;
    }

    public String getCpuset() {
        return cpuset;
    }

    public void setCpuset(String cpuset) {
        this.cpuset = cpuset;
    }

    public Boolean getAttachStdin() {
        return attachStdin;
    }

    public void setAttachStdin(Boolean attachStdin) {
        this.attachStdin = attachStdin;
    }

    public Boolean getAttachStdout() {
        return attachStdout;
    }

    public void setAttachStdout(Boolean attachStdout) {
        this.attachStdout = attachStdout;
    }

    public Boolean getAttachStderr() {
        return attachStderr;
    }

    public void setAttachStderr(Boolean attachStderr) {
        this.attachStderr = attachStderr;
    }

    public Boolean getTty() {
        return tty;
    }

    public void setTty(Boolean tty) {
        this.tty = tty;
    }

    public Boolean getOpenStdin() {
        return openStdin;
    }

    public void setOpenStdin(Boolean openStdin) {
        this.openStdin = openStdin;
    }

    public Boolean getStdinOnce() {
        return stdinOnce;
    }

    public void setStdinOnce(Boolean stdinOnce) {
        this.stdinOnce = stdinOnce;
    }

    public String[] getEnv() {
        return env;
    }

    public void setEnv(String[] env) {
        this.env = env;
    }

    public String[] getCmd() {
        return cmd;
    }

    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    public String[] getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String[] entrypoint) {
        this.entrypoint = entrypoint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public HashMap<String, HashMap<String, String>> getVolumes() {
        return volumes;
    }

    public void setVolumes(HashMap<String, HashMap<String, String>> volumes) {
        this.volumes = volumes;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public Boolean getNetworkDisabled() {
        return networkDisabled;
    }

    public void setNetworkDisabled(Boolean networkDisabled) {
        this.networkDisabled = networkDisabled;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public HashMap<String, HashMap<String, String>> getExposedPorts() {
        return exposedPorts;
    }

    public void setExposedPorts(HashMap<String, HashMap<String, String>> exposedPorts) {
        this.exposedPorts = exposedPorts;
    }

    public String[] getSecurityOpts() {
        return securityOpts;
    }

    public void setSecurityOpts(String[] securityOpts) {
        this.securityOpts = securityOpts;
    }

    public HostConfig getHostConfig() {
        return hostConfig;
    }

    public void setHostConfig(HostConfig hostConfig) {
        this.hostConfig = hostConfig;
    }
}
