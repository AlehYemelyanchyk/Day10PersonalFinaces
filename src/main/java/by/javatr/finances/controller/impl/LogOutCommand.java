package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class LogOutCommand extends AbstractCommandExecutor {
    private static final String SUCCESS = "Hope to see you soon!";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        getServiceFactory().getClientService().logOut(id);
        return SUCCESS + PARAMETER_DELIMITER + NO_SESSION_INT;
    }
}
