package ua.dmytrokashchenko.floristry.service.exception;

public class GettingAccessoryException extends Exception {
    public GettingAccessoryException() {
        super();
    }

    public GettingAccessoryException(String message) {
        super(message);
    }

    public GettingAccessoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public GettingAccessoryException(Throwable cause) {
        super(cause);
    }
}
