package com.sport.support.membership.entity.enumeration;

public enum Type {
    POOL(1),
    BRONZE(3),
    SILVER(5),
    GOLD(30),
    ONE_TIME(1);

    private final int loginAttempt;

    Type(int loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }
}
