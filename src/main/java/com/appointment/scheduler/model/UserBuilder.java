package com.appointment.scheduler.model;

import java.util.Arrays;

public class UserBuilder implements Builder<User> {
    private User user;
    private static UserBuilder builder;

    private UserBuilder() {
    }

    public static UserBuilder newBuilder() {
        if (builder == null) {
            builder = new UserBuilder();
        }
        builder.user = new User();
        return builder;
    }

    public UserBuilder withUserId(String userId) {
        this.user.setUserId(userId);
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder withFullName(String fullName) {
        this.user.setFullName(fullName);
        return this;
    }

    public UserBuilder withRoles(Role... roles) {
        this.user.setRoles(Arrays.asList(roles));
        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
