package com.appointment.scheduler.service;

import com.appointment.scheduler.constants.ApplicationConstants;
import com.appointment.scheduler.data.DataStore;
import com.appointment.scheduler.exception.InvalidCredentialsException;
import com.appointment.scheduler.exception.UnimplementedLogicException;
import com.appointment.scheduler.model.LoginData;
import com.appointment.scheduler.model.User;
import com.appointment.scheduler.switcher.DatabaseSwitcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateService {

    private DatabaseSwitcher switcher;

    public AuthenticateService(DatabaseSwitcher switcher) {
        this.switcher = switcher;
    }

    public User authenticate(User user) {
        if (switcher.isDemoModeOn()) {
            Optional<User> authenticatedUser = DataStore.authenticate(user);
            if (authenticatedUser.isPresent()) {
                return authenticatedUser.get();
            }
            throw new InvalidCredentialsException(user);
        } else {
            // TODO - Need implementation
            throw new UnimplementedLogicException(ApplicationConstants.UNIMPLEMENTED_LOGIC_MESSAGE);
        }
    }

    public LoginData logInAuthenticatedUser(User authenticatedUser) {
        if (switcher.isDemoModeOn()) {
            return DataStore.doLogin(authenticatedUser);
        } else {
            // TODO - Need implementation
            throw new UnimplementedLogicException(ApplicationConstants.UNIMPLEMENTED_LOGIC_MESSAGE);
        }
    }
}
