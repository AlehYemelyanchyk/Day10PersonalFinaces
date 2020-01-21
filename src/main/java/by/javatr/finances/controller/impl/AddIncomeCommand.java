package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/29/2019.
 */
public class AddIncomeCommand extends AbstractCommandExecutor {

    private final static String SUCCESS = "New income was added";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String accountName = attributes[0];
        int amount = Integer.valueOf(attributes[1]);
        String incomeName = attributes[2];
        String userName = sessionInfoSplit[0];
        getServiceFactory().getEntityService().deposit(userName, accountName, amount, incomeName);
        return SUCCESS + PARAMETER_DELIMITER + id;
    }
}
