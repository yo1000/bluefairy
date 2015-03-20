package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
public class Image {
    @JsonProperty("Created")
    private Long created;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("ParentId")
    private String parentId;
    @JsonProperty("RepoTags")
    private String[] repoTags;
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

    public String getParentIdToShort() {
        return this.getParentId() == null
                ? "<none>"
                : this.getParentId().length() > 12
                ? this.getParentId().substring(0, 12)
                : this.getParentId();
    }

    public Date getCreatedToDate() {
        return this.getCreated() != null
                ? new Date(this.getCreated() * 1000L)
                : null;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String[] getRepoTags() {
        return repoTags;
    }

    public void setRepoTags(String[] repoTags) {
        this.repoTags = repoTags;
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
