package by.javatr.finances.controller.impl;

import by.javatr.finances.dao.bean.Account;
import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class AddMoneyAccountCommand extends AbstractCommandExecutor {

    private final static String SUCCESS = "New account was created";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String accountName = attributes[0];
        String currency = attributes[1];
        String userName = sessionInfoSplit[0];
        getServiceFactory().getEntityService().add(userName, new Account(accountName, currency));
        return SUCCESS + PARAMETER_DELIMITER + id;
    }
}
