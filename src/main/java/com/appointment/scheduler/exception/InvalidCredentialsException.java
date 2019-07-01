package com.appointment.scheduler.exception;

import com.appointment.scheduler.init.AppConfig;
import com.appointment.scheduler.model.User;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InvalidCredentialsException extends RuntimeException implements ApplicationError {

    private static final long serialVersionUID = -1932919112193L;
    private final String message;
    private boolean passwordSupplied;
    private String errorTime;

    public InvalidCredentialsException(@NotNull User user) {
        this.passwordSupplied = !StringUtils.isEmpty(user.getPassword());
        this.message = "Invalid credentials supplied for user " + user.getUserId();
        this.errorTime = AppConfig.DATE_FORMATTER.format(new Date());
    }
}
