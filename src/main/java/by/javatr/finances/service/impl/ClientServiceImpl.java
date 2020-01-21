package by.javatr.finances.service.impl;

import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;
import by.javatr.finances.dao.factory.DAOFactory;
import by.javatr.finances.service.ClientService;
import by.javatr.finances.service.exception.ServiceException;
import by.javatr.finances.service.validation.ServiceValidation;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class ClientServiceImpl implements ClientService {
    private static final String PARAMETER_DELIMITER = ";";
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private EntityServiceImpl entityService = new EntityServiceImpl();

    @Override
    public void signIn(String login, String password, String email) throws ServiceException {
        if (!ServiceValidation.isLoginValid(login)) {
            throw new ServiceException("A login must contain at least 1 character");
        } else if (!ServiceValidation.isPasswordValid(password)) {
            throw new ServiceException("A password must contain at least 1 alphabetical character" +
                    " and at least 1 numeric character. It also must be at least 8 characters long " +
                    "with no special symbols");
        } else if (!ServiceValidation.isEmailValid(email)) {
            throw new ServiceException("The email address is not correct");
        }
        try {
            daoFactory.getUserDAO().save(new User(login, password, email));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeUser(String sessionId, String accounts, String password, String userName, String userPassword) throws ServiceException {
        if (!userPassword.equals(password)) {
            throw new ServiceException("User wasn't deleted. Wrong password");
        }
        String[] accountsSplit = accounts.split(PARAMETER_DELIMITER);
        for (String account : accountsSplit) {
            entityService.remove(userName, account);
        }
        try {
            deleteTemporalSession(sessionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        try {
            daoFactory.getUserDAO().delete(userName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(String login) throws ServiceException {
        User user;
        try {
            user = daoFactory.getUserDAO().get(login);
            if (user == null) {
                throw new ServiceException("User was not found");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public String logIn(String login, String password) throws ServiceException {
        String sessionId;
        User user;
        try {
            user = daoFactory.getUserDAO().get(login);
            if (user == null || !user.getPassword().equals(password)) {
                throw new ServiceException("Login or password is not correct");
            } else {
                sessionId = String.valueOf(user.hashCode() + Math.random());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        try {
            createTemporalSession(sessionId, user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return sessionId;
    }

    @Override
    public void logOut(String sessionId) throws ServiceException {
        try {
            deleteTemporalSession(sessionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getSession(String sessionId) throws ServiceException {
        String session;
        try {
            session = daoFactory.getSessionDAO().get(sessionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return session;
    }

    private void createTemporalSession(String sessionId, User user) throws DAOException {
        daoFactory.getSessionDAO().save(sessionId, user);
    }

    private void deleteTemporalSession(String sessionId) throws DAOException {
        daoFactory.getSessionDAO().delete(sessionId);
    }
}
