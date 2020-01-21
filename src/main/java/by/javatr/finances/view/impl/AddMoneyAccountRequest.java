package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class AddMoneyAccountRequest extends AbstractRequesterCurrencyAware {

    @Override
    protected String createRequest(String sessionId) {
        System.out.println("Enter account name:");
        String name = getScanner().readString();
        System.out.println("Choose account currency:");
        String currency = getCurrency();
        String attributes = name + PARAMETER_DELIMITER + currency + PARAMETER_DELIMITER;
        return sessionId + COMMAND_DELIMITER + attributes + COMMAND_DELIMITER + CommandName.ADD_MONEY_ACCOUNT_COMMAND;
    }
}
