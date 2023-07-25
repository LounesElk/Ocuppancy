package com.itso.occupancy.occupancy.helper.enumeration;

import java.util.concurrent.TimeUnit;

public enum SecurityJWTEnum {


    JWT_TOKEN_VALIDITY          (TimeUnit.HOURS.toMillis(8)),
    JWT_SHORT_TOKEN_VALIDITY    (TimeUnit.MINUTES.toMillis(5)),
    ACCOUNT_LOCK_TIMER          (TimeUnit.MINUTES.toMillis(5)),
    JWT_AUTH_MAX_ATTEMPT        (5L);

    private final Long timer;

    // constructor
    SecurityJWTEnum(Long timer) {
        this.timer = timer;
    }

    // Getter
    public Long get() {
        return timer;
    }
}
