package ua.dmytrokashchenko.floristry.service.exception;

public class GettingBouquetException extends Exception {
    public GettingBouquetException() {
        super();
    }

    public GettingBouquetException(String message) {
        super(message);
    }

    public GettingBouquetException(String message, Throwable cause) {
        super(message, cause);
    }

    public GettingBouquetException(Throwable cause) {
        super(cause);
    }
}
