package com.appointment.scheduler.data;

import com.appointment.scheduler.model.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DataStore {
    private DataStore() {
    }

    private static final List<User> users = new ArrayList<>();

    public static User createUser(User user) {
        int indexOfUser = users.indexOf(user);
        if (indexOfUser < 0) {
            users.add(user);
        } else {
            users.set(indexOfUser, user);
        }
        return user;
    }

    public static Optional<User> getUserById(String userId) {
        Predicate<User> userPredicate = stored -> userId.equals(stored.getUserId());
        return users.stream().filter(userPredicate).findFirst();
    }

    public static Optional<User> authenticate(User user) {
        Predicate<User> authenticatePredicate = stored -> user.getPassword().equals(stored.getPassword());
        return users.stream().filter(authenticatePredicate).findFirst();
    }

    public static List<User> allUsers() {
        return users;
    }
}
