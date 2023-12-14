package com.inops.visitorpass.model;

public enum Role {
	
	USER("User", 4), SUPER_USER("Super_User", 3), ADMIN("Admin", 2), SUPER_ADMIN("Super_Admin", 1);

	private final String value;
	private final int precidence;

	private Role(String value, int precidence) {
		this.value = value;
		this.precidence = precidence;
	}

	public String getValue() {
		return value;
	}

	public int getPrecidence() {
		return precidence;
	}
	
	 public static Role findByValue(String value) {
         for (Role role : values()) {
             if (role.getValue().equalsIgnoreCase(value)) {
                 return role;
             }
         }
         throw new IllegalArgumentException("No enum constant found with Role value: " + value);
     }
}
