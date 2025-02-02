package github.io.truongbn.spring_security_6.__one_time_token.exception;

public class EmailSendException extends RuntimeException {
    public EmailSendException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
