package com.appointment.scheduler.controller;

import com.appointment.scheduler.model.LoginData;
import com.appointment.scheduler.model.User;
import com.appointment.scheduler.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "/**")
public class AuthController {
	public AuthController(AuthenticateService service) {
		this.service = service;
	}

	private AuthenticateService service;

	@Autowired
	private HttpServletRequest request;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> authenticate(@RequestBody User user) {
		User authUser = service.authenticate(user);
		LoginData loginData = service.logInAuthenticatedUser(authUser);
		return new ResponseEntity<>(loginData, HttpStatus.OK);
	}

	private void verifyAndSetDemoMode() {

	}
}
