package by.javatr.finances.controller.exception;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class ControllerException extends Exception {
    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
