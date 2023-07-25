package com.inops.visitorpass.model;

public enum Role {
    USER("User"),
    SUPER_USER("Super_User"),
    ADMIN("Admin");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
