package com.appointment.scheduler.exception;

import com.appointment.scheduler.init.AppConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnimplementedLogicException extends RuntimeException implements ApplicationError {
    private static final long serialVersionUID = -123931239123L;
    private final String message;
    private String errorTime;

    public UnimplementedLogicException(String message) {
        this.message = message;
        this.errorTime = AppConfig.DATE_FORMATTER.format(new Date());
    }
}
