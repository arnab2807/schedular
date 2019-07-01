package com.appointment.scheduler.loader;

import com.appointment.scheduler.data.DataStore;
import com.appointment.scheduler.model.Role;
import com.appointment.scheduler.model.UserBuilder;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DataLoader {

    @PostConstruct
    public void loadUsers() {
        System.out.printf("\nUsers are being loaded............ Current users %d", DataStore.allUsers().size());
        DataStore.createUser(UserBuilder.newBuilder()
                .withFullName("System Administrator")
                .withPassword("1234")
                .withUserId("admin")
                .withRoles(Role.SYS_ADMIN)
                .build());
        DataStore.createUser(UserBuilder.newBuilder()
                .withFullName("Site User 1")
                .withPassword("1234")
                .withUserId("site_user_1")
                .withRoles(Role.SITE_USER)
                .build());
        DataStore.createUser(UserBuilder.newBuilder()
                .withFullName("System User")
                .withPassword("1234")
                .withUserId("sys_user")
                .withRoles(Role.SYS_USER)
                .build());

        System.out.printf("\nNo of user added %d\n", DataStore.allUsers().size());
    }

}
