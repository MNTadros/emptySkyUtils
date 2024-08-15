package com.empty.emptyskyutils.utils;

import java.io.Serializable;
import java.time.Instant;
import java.time.Duration;

public class playerData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uuid;
    private originType origin;
    private int level;
    private Instant personalTimer;

    public playerData(String uuid, originType origin, int level) {
        this.uuid = uuid;
        this.origin = origin;
        this.level = level;
        this.personalTimer = Instant.now().minus(Duration.ofMinutes(10)); // Initialize timer as expired
    }

    public String getUuid() {
        return uuid;
    }

    public originType getOrigin() {
        return origin;
    }

    public int getOriginLevel() {
        return level;
    }

    public Instant getOriginCooldown() {
        return personalTimer;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setOrigin(originType origin) {
        this.origin = origin;
    }

    public void setOriginLevel(int level) {
        this.level = level;
    }

    public void setOriginCooldown(Instant time) {
        this.personalTimer = time;
    }

    public boolean isCooldownExpired() {
        return Instant.now().isAfter(personalTimer.plus(Duration.ofMinutes(10)));
    }

    public void resetCooldown() {
        this.personalTimer = Instant.now();
    }
}
