package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 1/1/2020.
 */
public class ShowBalanceCommand extends AbstractCommandExecutor {
    private final static String SUCCESS = " balance: ";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String accountName = attributes[0];
        String userName = sessionInfoSplit[0];
        int balance = getServiceFactory().getEntityService().getBalance(userName, accountName);
        String transactions = getServiceFactory().getEntityService().getTransactions(userName, accountName);
        return accountName + SUCCESS + balance + transactions + PARAMETER_DELIMITER + id;
    }
}
