package com.appointment.scheduler.model;

import java.io.Serializable;

import com.appointment.scheduler.exception.ApplicationError;

import lombok.Data;

@Data
public class ErrorObject implements Serializable {
    private static final long serialVersionUID = -1323913812312L;
    private String message;
    private String passwordSupplied;
    private String errorTime;

    public static ErrorObject fromException(ApplicationError error) {
        ErrorObject eo = new ErrorObject();
        eo.message = error.getMessage();
        eo.passwordSupplied = error.isPasswordSupplied() ? "YES" : "NO";
        eo.errorTime = error.getErrorTime();
        return eo;
    }
}
