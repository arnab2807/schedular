package com.appointment.scheduler.data;

import com.appointment.scheduler.init.AppConfig;
import com.appointment.scheduler.model.LoginData;
import com.appointment.scheduler.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DataStore {
    private DataStore() {
    }

    private static final List<User> USERS = new ArrayList<>();
    private static final List<LoginData> LOGIN_DATA = new ArrayList<>();

    public static User createUser(User user) {
        int indexOfUser = USERS.indexOf(user);
        if (indexOfUser < 0) {
            USERS.add(user);
        } else {
            USERS.set(indexOfUser, user);
        }
        return user;
    }

    public static Optional<User> getUserById(String userId) {
        Predicate<User> userPredicate = stored -> userId.equals(stored.getUserId());
        return USERS.stream().filter(userPredicate).findFirst();
    }

    public static Optional<User> authenticate(User user) {
        Predicate<User> authenticatePredicate = stored -> (stored.getUserId().equals(user.getUserId())
                && stored.getPassword().equals(user.getPassword()));
        return USERS.stream().filter(authenticatePredicate).findFirst();
    }

    public static List<User> allUsers() {
        return USERS;
    }

    public static LoginData doLogin(User user) {
        LoginData loginData = new LoginData(user);
        Date now = new Date();
        loginData.setLoginTime(AppConfig.DATE_FORMATTER.format(now));
        loginData.setLastAccessMilis(now.getTime());
    }
}
