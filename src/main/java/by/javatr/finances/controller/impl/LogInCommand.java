package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class LogInCommand extends AbstractCommandExecutor {
    private final static String SUCCESS = "Welcome, ";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String login = attributes[0];
        String password = attributes[1];
        id = getServiceFactory().getClientService().logIn(login, password);
        return SUCCESS + login + PARAMETER_DELIMITER + id;
    }
}
