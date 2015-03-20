package com.yo1000.bluefairy.model.entity;

import java.util.Date;

/**
 * Created by yoichi.kikuchi on 15/02/22.
 */
public class ContainerPreference {
    private Date created;
    private Date expiration;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
