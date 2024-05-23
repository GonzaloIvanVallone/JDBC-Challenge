package security.jdbc.Exception;

public class DeletionException extends RuntimeException {
    public DeletionException(String msg, Exception e) {
        super(msg);
    }
}
