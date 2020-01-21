package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class ShowBalanceRequest extends AbstractRequesterAccountsAware {

    @Override
    protected String createRequest(String sessionId) {
        List<String> accounts = getAccounts(sessionId);
        showAccounts(accounts);
        String attributes = chooseAccount(accounts);
        if (BACK_COMMAND_STRING.equals(attributes)) {
            return sessionId + COMMAND_DELIMITER + EMPTY_ATTRIBUTES + COMMAND_DELIMITER + CommandName.BACK_COMMAND;
        }
        return sessionId + COMMAND_DELIMITER + attributes + COMMAND_DELIMITER + CommandName.SHOW_BALANCE_COMMAND;
    }
}
