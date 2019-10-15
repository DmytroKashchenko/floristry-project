package ua.dmytrokashchenko.floristry.service.exception;

public class FlowerGettingException extends Exception {
    public FlowerGettingException() {
        super();
    }

    public FlowerGettingException(String message) {
        super(message);
    }

    public FlowerGettingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowerGettingException(Throwable cause) {
        super(cause);
    }
}
