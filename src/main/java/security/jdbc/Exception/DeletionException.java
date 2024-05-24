package security.jdbc.Exception;

public class DeletionException extends RuntimeException {
    public DeletionException(String msg, Exception e) {
    }
    public DeletionException(String message) {
        super(message);
    }
}
