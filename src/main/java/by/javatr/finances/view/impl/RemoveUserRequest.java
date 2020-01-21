package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 1/9/2020.
 */
public class RemoveUserRequest extends AbstractRequesterAccountsAware {

    @Override
    protected String createRequest(String sessionId) {
        List<String> accounts = getAccounts(sessionId);
        StringBuilder sb = new StringBuilder();
        for (String account : accounts) {
            sb.append(account).append(PARAMETER_DELIMITER);
        }
        System.out.println("Enter password:");
        String password = getScanner().readString();
        String attributes = password + PARAMETER_DELIMITER + sb.toString();
        return sessionId + COMMAND_DELIMITER + attributes + COMMAND_DELIMITER + CommandName.REMOVE_USER_COMMAND;
    }
}
