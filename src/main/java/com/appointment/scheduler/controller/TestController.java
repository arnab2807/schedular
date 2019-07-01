package com.appointment.scheduler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return "{\"status\" : \"UP\"}";
    }
}

