package africa.semicolon.Exceptions;

public class FailedVerificationException extends RuntimeException {
    public FailedVerificationException(String message) {
        super(message);
    }
}
