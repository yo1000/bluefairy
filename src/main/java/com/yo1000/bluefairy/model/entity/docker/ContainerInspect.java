package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/03/16.
 */
public class ContainerInspect {
    @JsonProperty("AppArmorProfile")
    private String appArmorProfile;
    @JsonProperty("Args")
    private String[] args;
    @JsonProperty("Config")
    private Config config;
    @JsonProperty("Created")
    private String created;//2015-01-06T15:47:31.485331387Z
    @JsonProperty("Driver")
    private String driver;
    @JsonProperty("ExecDriver")
    private String execDriver;
    @JsonProperty("ExecIDs")
    private String[] execIDs;
    @JsonProperty("HostConfig")
    private HostConfig hostConfig;
    @JsonProperty("HostnamePath")
    private String hostnamePath;
    @JsonProperty("HostsPath")
    private String hostsPath;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("MountLabel")
    private String mountLabel;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("NetworkSettings")
    private NetworkSettings networkSettings;
    @JsonProperty("Path")
    private String path;
    @JsonProperty("ProcessLabel")
    private String processLabel;
    @JsonProperty("ResolvConfPath")
    private String resolvConfPath;
    @JsonProperty("RestartCount")
    private Integer restartCount;
    @JsonProperty("State")
    private State state;
    @JsonProperty("Volumes")
    private HashMap<String, String> volumes;
    @JsonProperty("VolumesRW")
    private HashMap<String, Boolean> volumesRW;

    public String getIdToShort() {
        return this.getId() == null
                ? "<none>"
                : this.getId().length() > 12
                ? this.getId().substring(0, 12)
                : this.getId();
    }

    public String getAppArmorProfile() {
        return appArmorProfile;
    }

    public void setAppArmorProfile(String appArmorProfile) {
        this.appArmorProfile = appArmorProfile;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getExecDriver() {
        return execDriver;
    }

    public void setExecDriver(String execDriver) {
        this.execDriver = execDriver;
    }

    public String[] getExecIDs() {
        return execIDs;
    }

    public void setExecIDs(String[] execIDs) {
        this.execIDs = execIDs;
    }

    public HostConfig getHostConfig() {
        return hostConfig;
    }

    public void setHostConfig(HostConfig hostConfig) {
        this.hostConfig = hostConfig;
    }

    public String getHostnamePath() {
        return hostnamePath;
    }

    public void setHostnamePath(String hostnamePath) {
        this.hostnamePath = hostnamePath;
    }

    public String getHostsPath() {
        return hostsPath;
    }

    public void setHostsPath(String hostsPath) {
        this.hostsPath = hostsPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMountLabel() {
        return mountLabel;
    }

    public void setMountLabel(String mountLabel) {
        this.mountLabel = mountLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NetworkSettings getNetworkSettings() {
        return networkSettings;
    }

    public void setNetworkSettings(NetworkSettings networkSettings) {
        this.networkSettings = networkSettings;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProcessLabel() {
        return processLabel;
    }

    public void setProcessLabel(String processLabel) {
        this.processLabel = processLabel;
    }

    public String getResolvConfPath() {
        return resolvConfPath;
    }

    public void setResolvConfPath(String resolvConfPath) {
        this.resolvConfPath = resolvConfPath;
    }

    public Integer getRestartCount() {
        return restartCount;
    }

    public void setRestartCount(Integer restartCount) {
        this.restartCount = restartCount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public HashMap<String, String> getVolumes() {
        return volumes;
    }

    public void setVolumes(HashMap<String, String> volumes) {
        this.volumes = volumes;
    }

    public HashMap<String, Boolean> getVolumesRW() {
        return volumesRW;
    }

    public void setVolumesRW(HashMap<String, Boolean> volumesRW) {
        this.volumesRW = volumesRW;
    }
}
