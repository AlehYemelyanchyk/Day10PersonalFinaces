package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/29/2019.
 */
public class RemoveMoneyAccountCommand extends AbstractCommandExecutor {

    private final static String SUCCESS = "Account was deleted";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String accountName = attributes[0];
        String userName = sessionInfoSplit[0];

        getServiceFactory().getEntityService().remove(userName, accountName);
        return SUCCESS + PARAMETER_DELIMITER + id;
    }
}
