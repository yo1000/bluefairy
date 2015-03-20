package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yoichi.kikuchi on 15/03/19.
 */
public class ImageInspect {
    @JsonProperty("Architecture")
    private String architecture;
    @JsonProperty("Author")
    private String author;
    @JsonProperty("Comment")
    private String comment;
    @JsonProperty("Config")
    private Config config;
    @JsonProperty("Container")
    private String container;
    @JsonProperty("ContainerConfig")
    private Config containerConfig;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("DockerVersion")
    private String dockerVersion;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Os")
    private String os;
    @JsonProperty("Parent")
    private String parent;
    @JsonProperty("Size")
    private Long size;
    @JsonProperty("VirtualSize")
    private Long virtualSize;

    public String getIdToShort() {
        return this.getId() == null
                ? "<none>"
                : this.getId().length() > 12
                ? this.getId().substring(0, 12)
                : this.getId();
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Config getContainerConfig() {
        return containerConfig;
    }

    public void setContainerConfig(Config containerConfig) {
        this.containerConfig = containerConfig;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDockerVersion() {
        return dockerVersion;
    }

    public void setDockerVersion(String dockerVersion) {
        this.dockerVersion = dockerVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getVirtualSize() {
        return virtualSize;
    }

    public void setVirtualSize(Long virtualSize) {
        this.virtualSize = virtualSize;
    }
}
