package openschool.java.springbootstarter.logging.exception;

public class HttpLoggingStartupException extends RuntimeException {
    public HttpLoggingStartupException(String message) {
        super(message);
    }
}
