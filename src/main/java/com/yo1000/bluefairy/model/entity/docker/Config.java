package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/03/17.
 */
public class Config {
    @JsonProperty("AttachStderr")
    private Boolean attachStderr;
    @JsonProperty("AttachStdin")
    private Boolean attachStdin;
    @JsonProperty("AttachStdout")
    private Boolean attachStdout;
    @JsonProperty("Cmd")
    private String[] cmd;
    @JsonProperty("CpuShares")
    private Integer cpuShares;
    @JsonProperty("Cpuset")
    private String cpuset;
    @JsonProperty("Domainname")
    private String domainname;
    @JsonProperty("Entrypoint")
    private String entrypoint;
    @JsonProperty("Env")
    private String[] env;
    @JsonProperty("ExposedPorts")
    private HashMap<String, HashMap<String, String>> exposedPorts;
    @JsonProperty("Hostname")
    private String hostname;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("MacAddress")
    private String macAddress;
    @JsonProperty("Memory")
    private Long memory;
    @JsonProperty("MemorySwap")
    private Long memorySwap;
    @JsonProperty("NetworkDisabled")
    private Boolean networkDisabled;
    @JsonProperty("OnBuild")
    private String[] onBuild;
    @JsonProperty("OpenStdin")
    private Boolean openStdin;
    @JsonProperty("PortSpecs")
    private String[] portSpecs;
    @JsonProperty("StdinOnce")
    private Boolean stdinOnce;
    @JsonProperty("PortBindings")
    private HashMap<String, PortBinding[]> portBindings;
    @JsonProperty("Tty")
    private Boolean tty;
    @JsonProperty("User")
    private String user;
    @JsonProperty("Volumes")
    private HashMap<String, HashMap<String, String>> volumes;
    @JsonProperty("WorkingDir")
    private String workingDir;

    public Boolean getAttachStderr() {
        return attachStderr;
    }

    public void setAttachStderr(Boolean attachStderr) {
        this.attachStderr = attachStderr;
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

    public String[] getCmd() {
        return cmd;
    }

    public void setCmd(String[] cmd) {
        this.cmd = cmd;
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

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String entrypoint) {
        this.entrypoint = entrypoint;
    }

    public String[] getEnv() {
        return env;
    }

    public void setEnv(String[] env) {
        this.env = env;
    }

    public HashMap<String, HashMap<String, String>> getExposedPorts() {
        return exposedPorts;
    }

    public void setExposedPorts(HashMap<String, HashMap<String, String>> exposedPorts) {
        this.exposedPorts = exposedPorts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
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

    public Boolean getNetworkDisabled() {
        return networkDisabled;
    }

    public void setNetworkDisabled(Boolean networkDisabled) {
        this.networkDisabled = networkDisabled;
    }

    public String[] getOnBuild() {
        return onBuild;
    }

    public void setOnBuild(String[] onBuild) {
        this.onBuild = onBuild;
    }

    public Boolean getOpenStdin() {
        return openStdin;
    }

    public void setOpenStdin(Boolean openStdin) {
        this.openStdin = openStdin;
    }

    public String[] getPortSpecs() {
        return portSpecs;
    }

    public void setPortSpecs(String[] portSpecs) {
        this.portSpecs = portSpecs;
    }

    public Boolean getStdinOnce() {
        return stdinOnce;
    }

    public void setStdinOnce(Boolean stdinOnce) {
        this.stdinOnce = stdinOnce;
    }

    public HashMap<String, PortBinding[]> getPortBindings() {
        return portBindings;
    }

    public void setPortBindings(HashMap<String, PortBinding[]> portBindings) {
        this.portBindings = portBindings;
    }

    public Boolean getTty() {
        return tty;
    }

    public void setTty(Boolean tty) {
        this.tty = tty;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
