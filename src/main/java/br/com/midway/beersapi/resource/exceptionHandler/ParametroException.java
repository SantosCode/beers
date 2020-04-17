package br.com.midway.beersapi.resource.exceptionHandler;

public class ParametroException extends RuntimeException {
    private Throwable cause;

    public ParametroException(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return this.cause.getMessage();
    }
}
