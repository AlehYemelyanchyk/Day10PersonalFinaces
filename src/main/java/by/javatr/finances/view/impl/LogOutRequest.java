package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class LogOutRequest extends AbstractRequester {
    @Override
    protected String createRequest(String sessionId) {
        return sessionId + COMMAND_DELIMITER + EMPTY_ATTRIBUTES + COMMAND_DELIMITER + CommandName.LOG_OUT_COMMAND;
    }
}
