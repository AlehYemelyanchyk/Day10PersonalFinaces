package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 1/9/2020.
 */
public class RemoveUserCommand extends AbstractCommandExecutor {
    private final static String SUCCESS = "User was deleted.";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String password = attributes[0];
        String accounts = attributes[1];
        String userName = sessionInfoSplit[0];
        String userPassword = sessionInfoSplit[1];

        getServiceFactory().getClientService().removeUser(id, accounts, password, userName, userPassword);
        return SUCCESS + PARAMETER_DELIMITER + NO_SESSION_INT;
    }
}
