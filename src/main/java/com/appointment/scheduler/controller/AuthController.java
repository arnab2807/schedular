package com.appointment.scheduler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.scheduler.exception.ApplicationError;
import com.appointment.scheduler.exception.DuplicateSessionException;
import com.appointment.scheduler.exception.InvalidCredentialsException;
import com.appointment.scheduler.model.ErrorObject;
import com.appointment.scheduler.model.LoginData;
import com.appointment.scheduler.model.User;
import com.appointment.scheduler.service.AuthenticateService;

@RestController
public class AuthController {
	public AuthController(AuthenticateService service) {
		this.service = service;
	}

	private AuthenticateService service;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> authenticate(@RequestBody User user) {
		User authUser = service.authenticate(user);
		LoginData loginData = service.logInAuthenticatedUser(authUser);
		return new ResponseEntity<>(loginData, HttpStatus.OK);
	}

	@ExceptionHandler(value = { 
			InvalidCredentialsException.class, 
			DuplicateSessionException.class })
	public ResponseEntity<Object> handleAuthenticateFailure(ApplicationError e) {
		return new ResponseEntity<>(ErrorObject.fromException(e), HttpStatus.FORBIDDEN);
	}
}
