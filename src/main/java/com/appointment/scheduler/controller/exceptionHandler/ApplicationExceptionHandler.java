package com.appointment.scheduler.controller.exceptionHandler;

import com.appointment.scheduler.exception.ApplicationError;
import com.appointment.scheduler.exception.DuplicateSessionException;
import com.appointment.scheduler.exception.InvalidCredentialsException;
import com.appointment.scheduler.exception.UnimplementedLogicException;
import com.appointment.scheduler.model.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@SuppressWarnings("all")
public class ApplicationExceptionHandler {
    private static final List<String> IGNORED_METHODS = Arrays.asList("getClass", "hashCode", "equals", "toString");

    //    @ExceptionHandler(value = {
//            InvalidCredentialsException.class,
//            DuplicateSessionException.class})
    public ResponseEntity<Object> handleAuthenticateFailure(InvalidCredentialsException e) {
        ErrorObject errorObject = ErrorObject.newInstance()
                .withError("passwordSupplied", e.isPasswordSupplied() ? "YES" : "NO")
                .withError("message", e.getMessage())
                .withError("errorTime", e.getErrorTime());

        return buildResponseEntity(errorObject, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Object> handleDuplicateSessionException(DuplicateSessionException e) {
        ErrorObject errorObject = ErrorObject.newInstance()
                .withError("message", e.getMessage())
                .withError("errorTime", e.getErrorTime());
        return buildResponseEntity(errorObject, HttpStatus.FORBIDDEN);
    }

    //    @ExceptionHandler(value = UnimplementedLogicException.class)
    public ResponseEntity<Object> handleUnimplementedLogicException(UnimplementedLogicException e) {
        ErrorObject errorObject = ErrorObject.newInstance()
                .withError("message", e.getMessage())
                .withError("errorTime", e.getErrorTime());
        return buildResponseEntity(errorObject, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            InvalidCredentialsException.class,
            DuplicateSessionException.class,
            UnimplementedLogicException.class})
    public ResponseEntity<Object> testHandleUnimplementedLogicException(ApplicationError e) {
        ErrorObject errorObject = ErrorObject.newInstance();
        Class type = e.getClass();

        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (IGNORED_METHODS.contains(methodName) || method.getParameterCount() != 0) {
                continue;
            }
            String propertyNameWithCaps = methodName.replace("get", "");
            String propertyName = propertyNameWithCaps.substring(0, 1).toLowerCase() + propertyNameWithCaps.substring(1);
            try {
                Object value = method.invoke(e);
                errorObject.withError(propertyName, value);
            } catch (Exception ex) {
                errorObject.withError("message", "Unable to resolve error object");
                //TODO Remove later
                ex.printStackTrace();
            }
            System.out.printf("Final ErrorObject - %s", errorObject);
        }
        return buildResponseEntity(errorObject, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Object> buildResponseEntity(Object obj, HttpStatus status) {
        return new ResponseEntity<>(obj, status);
    }
    private boolean isSetter(String methodName) {
        return methodName.contains("set");
    }
}
