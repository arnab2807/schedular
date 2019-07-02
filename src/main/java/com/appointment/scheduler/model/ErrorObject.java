package com.appointment.scheduler.model;

import java.util.HashMap;

public class ErrorObject extends HashMap<String, Object> {
    public static ErrorObject newInstance() {
        return new ErrorObject();
    }
    public ErrorObject withError(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public String toString() {
        return super.toString();
    }
}
