package com.appointment.scheduler.exception;

public interface ApplicationError {
    String getMessage();
    default boolean isPasswordSupplied() {
        return false;
    }
    String getErrorTime();
}
