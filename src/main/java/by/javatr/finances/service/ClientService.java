package by.javatr.finances.service;

import by.javatr.finances.dao.bean.User;
import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public interface ClientService {
    void signIn(String id, String password, String email) throws ServiceException;

    void removeUser(String id, String accounts, String password, String userName, String userPassword) throws ServiceException;

    User getUser(String id) throws ServiceException;

    String logIn(String id, String password) throws ServiceException;

    void logOut(String id) throws ServiceException;

    String getSession(String id)throws ServiceException;
}
