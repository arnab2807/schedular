package com.appointment.scheduler.controller;

import com.appointment.scheduler.exception.InvalidCredentialsException;
import com.appointment.scheduler.init.AppConfig;
import com.appointment.scheduler.model.ErrorObject;
import com.appointment.scheduler.model.LoginData;
import com.appointment.scheduler.model.User;
import com.appointment.scheduler.service.AuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AuthController {
    public  AuthController(AuthenticateService service) {
        this.service = service;
    }
    private AuthenticateService service;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> authenticate(@RequestBody User user) {
        User authUser = service.authenticate(user);
        LoginData loginData = new LoginData(authUser);
        Date now = new Date();
        loginData.setLoginTime(AppConfig.DATE_FORMATTER.format(now));
        loginData.setLastAccessMilis(now.getTime());
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }
    @ExceptionHandler(value = {InvalidCredentialsException.class})
    public ResponseEntity<Object> handleAuthenticateFailure(InvalidCredentialsException e) {
        return new ResponseEntity<>(ErrorObject.fromException(e), HttpStatus.FORBIDDEN);
    }
}
