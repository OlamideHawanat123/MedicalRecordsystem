package africa.semicolon.Exceptions;

public class EmptyDetailsException extends RuntimeException {
    public EmptyDetailsException(String message) {
        super(message);
    }
}
