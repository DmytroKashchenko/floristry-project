package ua.dmytrokashchenko.floristry.service.exception;

import java.util.function.Supplier;

public class UserLoginException extends Exception {
    public UserLoginException() {
        super();
    }

    public UserLoginException(String message) {
        super(message);
    }

    public UserLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLoginException(Throwable cause) {
        super(cause);
    }
}
