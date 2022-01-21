package br.com.validatepassword.services.exceptions;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException(String message) {
        super(message);
    }

    public PasswordInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
