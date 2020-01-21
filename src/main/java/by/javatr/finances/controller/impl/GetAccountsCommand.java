package by.javatr.finances.controller.impl;

import by.javatr.finances.dao.bean.User;
import by.javatr.finances.service.exception.ServiceException;

import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 1/11/2020.
 */
public class GetAccountsCommand extends AbstractCommandExecutor {
    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) throws ServiceException {
        String userName = sessionInfoSplit[0];
        User user = getServiceFactory().getClientService().getUser(userName);
        List<String> accounts = user.getAccounts();
        StringBuilder sb = new StringBuilder();
        for (String account : accounts) {
            sb.append(account);
        }
        return sb.toString();
    }
}
