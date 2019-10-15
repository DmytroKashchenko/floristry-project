package ua.dmytrokashchenko.floristry.service.exception;

public class AccessoryAddingException extends Exception {
    public AccessoryAddingException() {
        super();
    }

    public AccessoryAddingException(String message) {
        super(message);
    }

    public AccessoryAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessoryAddingException(Throwable cause) {
        super(cause);
    }
}
