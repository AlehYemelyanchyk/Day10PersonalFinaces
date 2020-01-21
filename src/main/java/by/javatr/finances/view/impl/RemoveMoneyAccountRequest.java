package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class RemoveMoneyAccountRequest extends AbstractRequesterAccountsAware {
    @Override
    protected String createRequest(String sessionId) {
        List<String> accounts = getAccounts(sessionId);
        showAccounts(accounts);
        String attributes = chooseAccount(accounts);
        return sessionId + COMMAND_DELIMITER + attributes + COMMAND_DELIMITER + CommandName.REMOVE_MONEY_ACCOUNT_COMMAND;
    }
}
