package com.inops.visitorpass.model;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
	SUPER_USER("SUPER_USER");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
