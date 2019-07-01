package com.appointment.scheduler.service;

import com.appointment.scheduler.data.DataStore;
import com.appointment.scheduler.exception.InvalidCredentialsException;
import com.appointment.scheduler.model.LoginData;
import com.appointment.scheduler.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateService {
	public User authenticate(User user) {
		Optional<User> authenticatedUser = DataStore.authenticate(user);
		if (authenticatedUser.isPresent()) {
			return authenticatedUser.get();
		}
		throw new InvalidCredentialsException(user);
	}

	public LoginData logInAuthenticatedUser(User authenticatedUser) {
		return DataStore.doLogin(authenticatedUser);
	}
}
