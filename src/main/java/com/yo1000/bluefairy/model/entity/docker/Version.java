package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
public class Version {
    @JsonProperty("Version")
    private String version;
    @JsonProperty("ApiVersion")
    private String apiVersion;
    @JsonProperty("GitCommit")
    private String gitCommit;
    @JsonProperty("Os")
    private String os;
    @JsonProperty("KernelVersion")
    private String kernelVersion;
    @JsonProperty("Arch")
    private String arch;
    @JsonProperty("GoVersion")
    private String goVersion;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getGitCommit() {
        return gitCommit;
    }

    public void setGitCommit(String gitCommit) {
        this.gitCommit = gitCommit;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getGoVersion() {
        return goVersion;
    }

    public void setGoVersion(String goVersion) {
        this.goVersion = goVersion;
    }
}
