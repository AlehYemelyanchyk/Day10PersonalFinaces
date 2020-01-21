package by.javatr.finances.view.impl;

import by.javatr.finances.controller.Controller;
import by.javatr.finances.controller.exception.ControllerException;
import by.javatr.finances.scanner.DataScanner;
import by.javatr.finances.view.Requester;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public abstract class AbstractRequester implements Requester {
    private DataScanner scanner = new DataScanner();
    private Controller controller = new Controller();
    public static final String ENUM_VALUES_DELIMITER = ";";
    public static final String PARAMETER_DELIMITER = ";";
    public static final String COMMAND_DELIMITER = " ";
    public static final String EMPTY_ATTRIBUTES = "";
    public static final int FIRST_RANGE_NUMBER = 0;
    public static final int ZERO_TO_ONE_SHIFT = 1;
    public static final int BACK_COMMAND_INT = 0;
    public static final String BACK_COMMAND_STRING = "0";

    @Override
    public String processRequest(String sessionId) {
        String commandResponse;
        String request = createRequest(sessionId);
        try {
            commandResponse = controller.executeTask(request);
        } catch (ControllerException e) {
            commandResponse = e.getMessage();
        }
        return commandResponse;
    }

    protected abstract String createRequest(String sessionId);

    public DataScanner getScanner() {
        return scanner;
    }

    public Controller getController() {
        return controller;
    }
}
