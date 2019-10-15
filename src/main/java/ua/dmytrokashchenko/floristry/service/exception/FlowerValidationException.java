package ua.dmytrokashchenko.floristry.service.exception;

public class FlowerValidationException extends Exception {
    public FlowerValidationException() {
        super();
    }

    public FlowerValidationException(String message) {
        super(message);
    }

    public FlowerValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowerValidationException(Throwable cause) {
        super(cause);
    }
}
