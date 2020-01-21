package by.javatr.finances.service.exception;

/**
 * @author Aleh Yemelyanchyk on 1/1/2020.
 */
public class SessionException extends ServiceException {
    public SessionException() {
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
