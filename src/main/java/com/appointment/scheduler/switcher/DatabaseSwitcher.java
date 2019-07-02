package com.appointment.scheduler.switcher;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class DatabaseSwitcher {
    @Autowired
    private HttpServletRequest request;

    private final boolean configuredDemoMode;

    private boolean currentDemoMode;

    public DatabaseSwitcher(@Value("${application.demoMode}") boolean configuredDemoMode) {
        this.configuredDemoMode = configuredDemoMode;
    }

    @Before("within(com.appointment.scheduler.controller.*)")
    public void checkDemoSwitch() {
        String demoHeader = request.getHeader("Demo-Mode");
        if(demoHeader != null) {
            boolean headerDemoMode = Boolean.parseBoolean(demoHeader);
            this.currentDemoMode = headerDemoMode ;
        } else {
            System.out.printf("Header is null.. setting the original configured value..%s", this.configuredDemoMode);
            this.currentDemoMode = configuredDemoMode;
        }
        System.out.printf("\nCurrentDemoMode................%s\n", currentDemoMode);
    }

    public boolean isDemoModeOn() {
        return this.currentDemoMode;
    }
}
