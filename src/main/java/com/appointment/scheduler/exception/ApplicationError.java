package com.appointment.scheduler.exception;

import java.util.Date;

public interface ApplicationError {
    String getMessage();
    boolean isPasswordSupplied();
    Date getErrorTime();
}
