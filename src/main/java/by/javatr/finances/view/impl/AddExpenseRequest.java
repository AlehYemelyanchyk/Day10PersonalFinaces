package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class AddExpenseRequest extends AbstractRequesterAccountsAware {
    @Override
    protected String createRequest(String sessionId) {
        List<String> accounts = getAccounts(sessionId);
        showAccounts(accounts);
        String accountName = chooseAccount(accounts);
        if (BACK_COMMAND_STRING.equals(accountName)) {
            return sessionId + COMMAND_DELIMITER + EMPTY_ATTRIBUTES + COMMAND_DELIMITER + CommandName.BACK_COMMAND;
        }
        System.out.println("Enter expense amount:");
        int amount = getScanner().readInteger();
        System.out.println("Enter expense name:");
        String expenseName = getScanner().readString();
        String attributes = accountName + PARAMETER_DELIMITER + amount + PARAMETER_DELIMITER + expenseName;
        return sessionId + COMMAND_DELIMITER + attributes + COMMAND_DELIMITER + CommandName.ADD_EXPENSE_COMMAND;
    }
}
