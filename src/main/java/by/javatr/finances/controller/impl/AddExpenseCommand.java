package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/29/2019.
 */
public class AddExpenseCommand extends AbstractCommandExecutor {

    private final static String SUCCESS = "New expense was added";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String accountName = attributes[0];
        int amount = Integer.valueOf(attributes[1]);
        String expenseName = attributes[2];
        String userName = sessionInfoSplit[0];
        getServiceFactory().getEntityService().withdrawal(userName, accountName, amount, expenseName);
        return SUCCESS + PARAMETER_DELIMITER + id;
    }
}
