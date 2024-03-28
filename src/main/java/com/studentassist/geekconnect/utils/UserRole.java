package com.studentassist.geekconnect.utils;

public enum UserRole {
    STUDENT("STUDENT"),
    TA("TA"),
    PROFESSOR("PROFESSOR"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromString(String text) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.role.equalsIgnoreCase(text)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
