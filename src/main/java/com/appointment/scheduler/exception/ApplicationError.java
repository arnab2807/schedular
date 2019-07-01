package com.appointment.scheduler.exception;

import java.util.Date;

public interface ApplicationError {
    String getMessage();
    default boolean isPasswordSupplied() {
        return false;
    }
    String getErrorTime();
}
