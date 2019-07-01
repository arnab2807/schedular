package com.appointment.scheduler.exception;

import com.appointment.scheduler.init.AppConfig;
import com.appointment.scheduler.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DuplicateSessionException extends RuntimeException implements ApplicationError {
    private static final long serialVersionUID = -1932919112193L;
    private String message;
    private String errorTime;

    public DuplicateSessionException(User user) {
        this.message = "User " + user.getUserId() + " is already logged in.";
        this.errorTime = AppConfig.DATE_FORMATTER.format(new Date());
    }
}
