package com.empty.emptyskyutils.utils;

import java.io.Serializable;

public class playerData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uuid;
    private originType origin;

    public playerData(String uuid, originType origin) {
        this.uuid = uuid;
        this.origin = origin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public originType getOrigin() {
        return origin;
    }

    public void setOrigin(originType origin) {
        this.origin = origin;
    }
}
