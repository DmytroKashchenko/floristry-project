package ua.dmytrokashchenko.floristry.service.exception;

public class BouquetAddingException extends Exception {
    public BouquetAddingException() {
        super();
    }

    public BouquetAddingException(String message) {
        super(message);
    }

    public BouquetAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BouquetAddingException(Throwable cause) {
        super(cause);
    }
}
