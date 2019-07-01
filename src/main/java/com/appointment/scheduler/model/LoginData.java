package com.appointment.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Data
public class LoginData implements Serializable {
    private static final long serialVersionUID = -9423421039931L;

    public LoginData() {}

    public LoginData(User user) {
        this.loggedInUser = user;
        this.sessionId = UUID.randomUUID().toString().replace("-", "");
    }

    private String sessionId;
    private String loginTime;
    private User loggedInUser;
    private long inactiveCounter;
    private long lastAccessMilis;
}
