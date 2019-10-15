package ua.dmytrokashchenko.floristry.service.exception;

public class AccessoryValidationException extends Exception {
    public AccessoryValidationException() {
        super();
    }

    public AccessoryValidationException(String message) {
        super(message);
    }

    public AccessoryValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessoryValidationException(Throwable cause) {
        super(cause);
    }
}
