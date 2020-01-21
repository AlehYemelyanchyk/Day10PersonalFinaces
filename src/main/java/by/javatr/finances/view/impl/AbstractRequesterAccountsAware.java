package by.javatr.finances.view.impl;

import by.javatr.finances.controller.CommandName;
import by.javatr.finances.controller.exception.ControllerException;

import java.util.*;

/**
 * @author Aleh Yemelyanchyk on 1/8/2020.
 */
public abstract class AbstractRequesterAccountsAware extends AbstractRequester {

    protected List<String> getAccounts(String sessionId) {
        String request = sessionId + COMMAND_DELIMITER + EMPTY_ATTRIBUTES + COMMAND_DELIMITER + CommandName.GET_ACCOUNTS_COMMAND;
        String accounts = null;
        try {
            String response = getController().executeTask(request);
            String[] responseSplit = response.split(PARAMETER_DELIMITER);
            accounts = responseSplit[0];
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        String[] accountsSplit = accounts.split(PARAMETER_DELIMITER);
        List<String> accountsList = new ArrayList<>(Arrays.asList(accountsSplit));
        return accountsList;
    }

    protected void showAccounts(List<String> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + ZERO_TO_ONE_SHIFT) + ". " + accounts.get(i));
        }
    }

    protected String chooseAccount(List<String> accounts) {
        System.out.println("\n0. To main menu.");
        System.out.println("Choose account:");
        int menuChoice = getScanner().readIntegerInRange(FIRST_RANGE_NUMBER, accounts.size());
        if (menuChoice == BACK_COMMAND_INT) {
            return BACK_COMMAND_STRING;
        }
        return accounts.get(menuChoice - ZERO_TO_ONE_SHIFT);
    }
}
