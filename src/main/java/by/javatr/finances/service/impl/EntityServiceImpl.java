package by.javatr.finances.service.impl;

import by.javatr.finances.dao.bean.Account;
import by.javatr.finances.model.CurrencyType;
import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;
import by.javatr.finances.dao.factory.DAOFactory;
import by.javatr.finances.service.EntityService;
import by.javatr.finances.service.exception.ServiceException;
import by.javatr.finances.service.exception.SessionException;

import java.util.List;
import java.util.Queue;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */

public class EntityServiceImpl implements EntityService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private static final String PARAMETER_DELIMITER = ";";
    private static final String TRANSACTION_DELIMITER = " : ";
    private static final String NEGATIVE_TRANSACTION = "-";
    private static final String NEW_LINE = "\n";

    @Override
    public void add(String userName, Account account) throws ServiceException {
        if (userName == null) {
            throw new ServiceException("An entity wasn't created. Wrong session id");
        } else if (account == null) {
            throw new ServiceException("An entity wasn't created. Wrong entity name");
        }
        try {
            User user = daoFactory.getUserDAO().get(userName);
            List<String> accounts = user.getAccounts();
            if (accounts.contains(account.getName())) {
                throw new ServiceException("An account with such name already exists");
            }
            accounts.add(account.getName());
            daoFactory.getFinancesEntityDAO().save(userName, account);
            daoFactory.getUserDAO().delete(user.getLogin());
            daoFactory.getUserDAO().save(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(String userName, String accountName) throws ServiceException {
        if (userName == null) {
            throw new SessionException("The entity wasn't removed. Wrong user name");
        } else if (accountName == null) {
            throw new ServiceException("The entity wasn't removed. Wrong entity name");
        }
        try {
            User user = daoFactory.getUserDAO().get(userName);
            user.getAccounts().remove(accountName);
            daoFactory.getUserDAO().delete(user.getLogin());
            daoFactory.getUserDAO().save(user);
            daoFactory.getFinancesEntityDAO().delete(userName, accountName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deposit(String id, String accountName, int amount, String depositName) throws ServiceException {

        try {
            Account account = (Account) daoFactory.getFinancesEntityDAO().get(id, accountName);
            int balance = account.getBalance();
            Queue<String> transactions = account.getTransactions();
            String transaction = amount + TRANSACTION_DELIMITER + depositName;
            transactions.offer(transaction);
            account.setBalance(balance + amount);
            remove(id, accountName);
            add(id, account);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void withdrawal(String id, String accountName, int amount, String withdrawalName) throws ServiceException {
        try {
            Account account = (Account) daoFactory.getFinancesEntityDAO().get(id, accountName);
            int balance = account.getBalance();
            if (balance < amount) {
                throw new ServiceException("Not enough money");
            }
            Queue<String> transactions = account.getTransactions();
            String transaction = NEGATIVE_TRANSACTION + amount + TRANSACTION_DELIMITER + withdrawalName;
            transactions.offer(transaction);
            account.setBalance(balance - amount);
            remove(id, accountName);
            add(id, account);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getBalance(String id, String accountName) throws ServiceException {
        try {
            Account account = (Account) daoFactory.getFinancesEntityDAO().get(id, accountName);
            return account.getBalance();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getTransactions(String id, String accountName) throws ServiceException {
        Queue<String> transactions;
        StringBuilder sb = new StringBuilder();
        try {
            Account account = (Account) daoFactory.getFinancesEntityDAO().get(id, accountName);
            transactions = account.getTransactions();
            sb.append(NEW_LINE);
            for (String transaction : transactions) {
                sb.append(transaction).append(NEW_LINE);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return sb.toString();
    }

    @Override
    public String getCurrencies() {
        StringBuilder sb = new StringBuilder();
        for (CurrencyType currency : CurrencyType.values()) {
            sb.append(currency).append(PARAMETER_DELIMITER);
        }
        return sb.toString();
    }
}
