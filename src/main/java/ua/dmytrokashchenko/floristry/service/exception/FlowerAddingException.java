package ua.dmytrokashchenko.floristry.service.exception;

public class FlowerAddingException extends Exception {
    public FlowerAddingException() {
        super();
    }

    public FlowerAddingException(String message) {
        super(message);
    }

    public FlowerAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowerAddingException(Throwable cause) {
        super(cause);
    }
}
