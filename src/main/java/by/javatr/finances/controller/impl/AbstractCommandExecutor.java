package by.javatr.finances.controller.impl;

import by.javatr.finances.controller.Command;
import by.javatr.finances.service.exception.ServiceException;
import by.javatr.finances.service.factory.ServiceFactory;

/**
 * @author Aleh Yemelyanchyk on 12/30/2019.
 */
public abstract class AbstractCommandExecutor implements Command {

    public static final String PARAMETER_DELIMITER = ";";
    public static final String EMPTY_ATTRIBUTES = "";
    public static final String NO_SESSION_STRING = "0";
    public static final int NO_SESSION_INT = 0;
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(String sessionId, String attributes) throws ServiceException {
        String[] splitAttributes = attributes.split(PARAMETER_DELIMITER);

        String sessionInfo = EMPTY_ATTRIBUTES;
        if (!sessionId.equals(NO_SESSION_STRING) && sessionId.length() > 0) {
            sessionInfo = serviceFactory.getClientService().getSession(sessionId);
        }

        String[] sessionInfoSplit = sessionInfo.split(PARAMETER_DELIMITER);

        String response = executeCommand(sessionId, splitAttributes, sessionInfoSplit);
        return response + PARAMETER_DELIMITER + sessionId;
    }

    protected abstract String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException;

    public ServiceFactory getServiceFactory() {
        return serviceFactory;
    }
}
