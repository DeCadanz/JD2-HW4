package by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.exceptions;

public class StorageException extends RuntimeException {
    public StorageException() {

    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }
}