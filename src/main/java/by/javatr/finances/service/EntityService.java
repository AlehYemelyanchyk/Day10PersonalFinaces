package by.javatr.finances.service;

import by.javatr.finances.dao.bean.Account;
import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public interface EntityService {
    void add(String id, Account account) throws ServiceException;

    void remove(String id, String accountName) throws ServiceException;

    void deposit(String id, String accountName, int amount, String depositName) throws ServiceException;

    void withdrawal(String id, String accountName, int amount, String withdrawalName) throws ServiceException;

    int getBalance(String id, String accountName) throws ServiceException;

    String getTransactions(String id, String accountName) throws ServiceException;

    String getCurrencies() throws ServiceException;

}
