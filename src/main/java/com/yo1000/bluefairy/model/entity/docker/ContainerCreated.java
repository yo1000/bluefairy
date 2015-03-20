package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yoichi.kikuchi on 15/03/15.
 */
public class ContainerCreated {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Warnings")
    private String[] warnings;

    public String getIdToShort() {
        return this.getId() == null
                ? "<none>"
                : this.getId().length() > 12
                ? this.getId().substring(0, 12)
                : this.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getWarnings() {
        return warnings;
    }

    public void setWarnings(String[] warnings) {
        this.warnings = warnings;
    }
}
