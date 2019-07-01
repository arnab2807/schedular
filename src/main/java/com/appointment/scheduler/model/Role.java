package com.appointment.scheduler.model;

public enum Role {
    SYS_ADMIN("System Administrator. Has all access to the system."),
    SYS_USER("System User. Can modify own settings but has limited access to other resources."),
    SITE_USER("User with read-only access.");

    private String roleDescription;

    Role(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleDescription() {
        return this.roleDescription;
    }
}
