package ua.dmytrokashchenko.floristry.service.exception;

public class AccessoryRemovalException extends Exception {
    public AccessoryRemovalException() {
        super();
    }

    public AccessoryRemovalException(String message) {
        super(message);
    }

    public AccessoryRemovalException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessoryRemovalException(Throwable cause) {
        super(cause);
    }
}
