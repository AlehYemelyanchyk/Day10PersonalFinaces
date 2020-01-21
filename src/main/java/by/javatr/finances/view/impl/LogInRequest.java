package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class LogInRequest extends AbstractRequester{
    @Override
    protected String createRequest(String sessionId) {
        System.out.println("Enter login:");
        String login = getScanner().readString();
        System.out.println("Enter password:");
        String password = getScanner().readString();
        String attributes = login + PARAMETER_DELIMITER + password;
        return sessionId + COMMAND_DELIMITER + attributes + COMMAND_DELIMITER + CommandName.LOG_IN_COMMAND;
    }
}
