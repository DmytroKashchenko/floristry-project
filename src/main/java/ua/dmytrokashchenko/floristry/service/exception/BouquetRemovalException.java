package ua.dmytrokashchenko.floristry.service.exception;

public class BouquetRemovalException extends Exception {
    public BouquetRemovalException() {
        super();
    }

    public BouquetRemovalException(String message) {
        super(message);
    }

    public BouquetRemovalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BouquetRemovalException(Throwable cause) {
        super(cause);
    }
}
