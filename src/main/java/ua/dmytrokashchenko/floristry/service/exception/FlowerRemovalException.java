package ua.dmytrokashchenko.floristry.service.exception;

public class FlowerRemovalException extends Exception {
    public FlowerRemovalException() {
        super();
    }

    public FlowerRemovalException(String message) {
        super(message);
    }

    public FlowerRemovalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowerRemovalException(Throwable cause) {
        super(cause);
    }
}
