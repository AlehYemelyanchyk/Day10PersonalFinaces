package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class SignInCommand extends AbstractCommandExecutor {
    private static final String SUCCESS = "New user was created. Welcome, ";


    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String login = attributes[0];
        String email = attributes[1];
        String password = attributes[2];
        getServiceFactory().getClientService().signIn(login, password, email);
        id = getServiceFactory().getClientService().logIn(login, password);
        return SUCCESS + login + PARAMETER_DELIMITER + id;
    }
}
