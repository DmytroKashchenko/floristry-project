package ua.dmytrokashchenko.floristry.service.exception;

public class BouquetValidatorException extends Exception {
    public BouquetValidatorException() {
        super();
    }

    public BouquetValidatorException(String message) {
        super(message);
    }

    public BouquetValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BouquetValidatorException(Throwable cause) {
        super(cause);
    }
}
