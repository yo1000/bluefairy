package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
public class Container {
    @JsonProperty("Command")
    private String command;
    @JsonProperty("Created")
    private Long created;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("Names")
    private String[] names;
    @JsonProperty("Ports")
    private Port[] ports;
    @JsonProperty("Status")
    private String status;

    public String getIdToShort() {
        return this.getId() == null
                ? "<none>"
                : this.getId().length() > 12
                ? this.getId().substring(0, 12)
                : this.getId();
    }

    public Date getCreatedToDate() {
        return this.getCreated() != null
                ? new Date(this.getCreated() * 1000L)
                : null;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Port[] getPorts() {
        return ports;
    }

    public void setPorts(Port[] ports) {
        this.ports = ports;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
