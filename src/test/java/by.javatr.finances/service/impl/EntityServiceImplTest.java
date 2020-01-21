package by.javatr.finances.service.impl;

import by.javatr.finances.dao.bean.Account;
import by.javatr.finances.dao.bean.FinancesEntity;
import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;
import by.javatr.finances.dao.factory.DAOFactory;
import by.javatr.finances.dao.impl.FileFinancesEntityDAO;
import by.javatr.finances.dao.impl.FileUserDAO;
import by.javatr.finances.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 1/1/2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class EntityServiceImplTest {

    private EntityServiceImpl entityService = new EntityServiceImpl();
    private FileFinancesEntityDAO entityDAO;
    private FileUserDAO userDAO;
    private User innerUser;
    private FinancesEntity innerEntity;

    @Mock
    private DAOFactory daoFactory;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field field = entityService.getClass().getDeclaredField("daoFactory");
        field.setAccessible(true);
        field.set(entityService, daoFactory);

        entityDAO = new FileFinancesEntityDAO() {
            @Override
            public FinancesEntity get(String id, String entityName) {
                return new FinancesEntity(entityName, "BYN");
            }

            @Override
            public void save(String id, FinancesEntity entity) {
                innerEntity = entity;
            }

            @Override
            public void delete(String id, String entityName) {
                innerEntity = null;
            }
        };

        userDAO = new FileUserDAO() {
            @Override
            public User get(String login) throws DAOException {
                if (innerUser != null && innerUser.getLogin().equals(login)) {
                    return innerUser;
                }
                String loginExpected = "User";
                if (login != null && !loginExpected.equals(login)) {
                    throw new DAOException("A user with such name does not exist");
                }
                return new User(loginExpected, "1234qwer", "mail@mail");
            }

            @Override
            public void save(User user) {
                innerUser = user;
            }

            @Override
            public void delete(String login) {
                innerUser = null;
            }
        };
        Mockito.when(daoFactory.getFinancesEntityDAO()).thenReturn(entityDAO);
        Mockito.when(daoFactory.getUserDAO()).thenReturn(userDAO);
    }

    @Test
    public void addTest() throws ServiceException {
        String expected = "Bank";
        entityService.add("User", new Account(expected, "BYN"));
        List<String> accounts = innerUser.getAccounts();
        Assert.assertNotNull(accounts);
        String actual = accounts.get(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addInvalidUserNameTest() {
        String expected = "A user with such name does not exist";
        String actual = "User";
        try {
            entityService.add("invalid", new Account(expected, "BYN"));
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void addNullUserNameTest() {
        String expected = "An entity wasn't created. Wrong session id";
        String actual = null;
        try {
            entityService.add(null, new Account(expected, "BYN"));
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addNullAccountTest() {
        String expected = "An entity wasn't created. Wrong entity name";
        String actual = null;
        try {
            entityService.add("User", null);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addExistingAccountTest() throws ServiceException {
        String expected = "An account with such name already exists";
        String actual = null;
        String userName = "User";
        String accountName = "Bank";
        entityService.add(userName, new Account(accountName, "BYN"));
        try {
            entityService.add(userName, new Account(accountName, "BYN"));
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeTest() throws ServiceException {
        int expected = 0;
        String userName = "User";
        String accountName = "Bank";
        entityService.add(userName, new Account(accountName, "BYN"));
        entityService.remove(userName, accountName);
        List<String> accounts = innerUser.getAccounts();
        Assert.assertNotNull(accounts);
        int actual = accounts.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeInvalidUserNameTest() throws ServiceException {
        String expected = "A user with such name does not exist";
        String actual = null;
        String userName = "User";
        String accountName = "Bank";
        entityService.add(userName, new Account(accountName, "BYN"));
        try {
            entityService.remove("invalid", accountName);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void removeNullUserNameTest() throws ServiceException {
        String expected = "The entity wasn't removed. Wrong user name";
        String actual = null;
        String userName = "User";
        String accountName = "Bank";
        entityService.add(userName, new Account(accountName, "BYN"));
        try {
            entityService.remove(null, accountName);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeInvalidAccountNameTest() throws ServiceException {
        String expected = "A user with such name does not exist";
        String actual = null;
        String userName = "User";
        String accountName = "Bank";
        entityService.add(userName, new Account(accountName, "BYN"));
        try {
            entityService.remove("invalid", accountName);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void removeNullAccountNameTest() throws ServiceException {
        String expected = "The entity wasn't removed. Wrong entity name";
        String actual = null;
        String userName = "User";
        String accountName = "Bank";
        entityService.add(userName, new Account(accountName, "BYN"));
        try {
            entityService.remove(userName, null);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }
}