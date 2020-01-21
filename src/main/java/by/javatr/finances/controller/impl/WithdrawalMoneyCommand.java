package by.javatr.finances.controller.impl;

import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/31/2019.
 */
public class WithdrawalMoneyCommand extends AbstractCommandExecutor {
    private final static String SUCCESS = "Withdrawal is successful";
    private final static String WITHDRAWAL_NAME = "Withdrawal";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String accountName = attributes[0];
        int amount = Integer.valueOf(attributes[1]);
        String userName = sessionInfoSplit[0];
        getServiceFactory().getEntityService().withdrawal(userName, accountName, amount, WITHDRAWAL_NAME);
        return SUCCESS + PARAMETER_DELIMITER + id;
    }
}
