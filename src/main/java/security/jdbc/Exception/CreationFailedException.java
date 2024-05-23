package security.jdbc.Exception;

public class CreationFailedException extends RuntimeException{
    public CreationFailedException(String message) {
        super(message);
    }
    public CreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
