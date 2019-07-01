package com.appointment.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = -1594893120L;
    private String userId;
    private String password;
    private String fullName;
    private List<Role> roles;

    @Override
    public int compareTo(User o) {
        if (this.userId == o.userId) {
            return 0;
        }
        if (o == null || o.getUserId() == null) {
            return 1;
        }
        if (this.userId == null) {
            return -1;
        }
        return this.userId.compareTo(o.userId);
    }

    public int hashCode() {
        if (this.userId == null) {
            return 0;
        }
        return userId.hashCode();
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof User) {
            User otherUser = (User) other;
            if (this == otherUser) {
                return true;
            }
            return compareTo(otherUser) == 0;
        }
        return false;
    }

}
