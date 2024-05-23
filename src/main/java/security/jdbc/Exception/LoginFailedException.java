package security.jdbc.Exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String userNotFound) {
    }
}
