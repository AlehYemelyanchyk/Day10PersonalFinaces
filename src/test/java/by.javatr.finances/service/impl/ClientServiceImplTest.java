package by.javatr.finances.service.impl;

import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.factory.DAOFactory;
import by.javatr.finances.dao.impl.FileSessionDAO;
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


/**
 * @author Aleh Yemelyanchyk on 1/6/2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
    //Создаю инстанс clientService - класса, который буду тестировать
    private ClientServiceImpl clientService = new ClientServiceImpl();
    // поле для сравнения с передаваемым юзером в тесте
    private User innerUser;
    private String temporalSession;
    private String testSessionId;
    private FileUserDAO userDAO;
    private FileSessionDAO sessionDAO;

    // Создаю моки daoFactory
    @Mock
    private DAOFactory daoFactory;

    // Этот код с подменой будет запускаться перед каждым тестовым методом
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // На 24 строке создается clientService внутри которого автоматом инициализируется поле daoFactory.
        // С помощью рефлексии я подменяю это поле на свой Mock daoFactory:
        Field field = clientService.getClass().getDeclaredField("daoFactory");
        field.setAccessible(true);
        field.set(clientService, daoFactory);

        // Задаю желаемое поведение userDAO.save() не с помощью мока, а через анонимный класс
        // Так я могу видеть что метод был запущен и сработал
        userDAO = new FileUserDAO() {
            @Override
            public void save(User user) {
                // сохраняю user для последующего сравнения
                innerUser = user;
            }

            @Override
            public User get(String login) {
                if (login == null || !login.equals(innerUser.getLogin())) {
                    return null;
                }
                return innerUser;
            }

            @Override
            public void delete(String login) {
                innerUser = null;
            }
        };

        sessionDAO = new FileSessionDAO() {
            @Override
            public void save(String sessionId, User user) {
                testSessionId = sessionId;
                temporalSession = "session";
            }

            @Override
            public String get(String sessionId) {
                if (sessionId == null || !sessionId.equals(testSessionId)) {
                    return null;
                }
                return temporalSession;
            }

            @Override
            public void delete(String sessionId) {
                if (sessionId != null && sessionId.equals(testSessionId))
                    temporalSession = null;
            }
        };
        // Указываю, что если вызывается daoFactory.getUserDAO(), он должен вернуть мок userDAO,
        // который я создал для тестов.
        Mockito.when(daoFactory.getUserDAO()).thenReturn(userDAO);
        Mockito.when(daoFactory.getSessionDAO()).thenReturn(sessionDAO);
    }

    // Проверяю, создается ли юзер с указанными параметрами
    @Test
    public void signInTest() throws ServiceException {
        String login = "sand";
        String password = "1234qwer";
        String email = "aleh@mail";
        clientService.signIn(login, password, email);
        Assert.assertNotNull(innerUser);
        Assert.assertEquals(login, innerUser.getLogin());
        Assert.assertEquals(password, innerUser.getPassword());
        Assert.assertEquals(email, innerUser.getEmail());
    }

    @Test
    public void signInEmptyLoginTest() {
        String expected = "A login must contain at least 1 character";
        String actual = null;
        try {
            clientService.signIn("", "1234qwer", "aleh@mail");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signInNullLoginTest() {
        String expected = "A login must contain at least 1 character";
        String actual = null;
        try {
            clientService.signIn(null, "1234qwer", "aleh@mail");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signInShortPasswordTest() {
        String expected = "A password must contain at least 1 alphabetical character" +
                " and at least 1 numeric character. It also must be at least 8 characters long " +
                "with no special symbols";
        String actual = null;
        try {
            clientService.signIn("aleh", "12e", "aleh@mail");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signInNullPasswordTest() {
        String expected = "A password must contain at least 1 alphabetical character" +
                " and at least 1 numeric character. It also must be at least 8 characters long " +
                "with no special symbols";
        String actual = null;
        try {
            clientService.signIn("aleh", null, "aleh@mail");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signInInvalidPasswordTest() {
        String expected = "A password must contain at least 1 alphabetical character" +
                " and at least 1 numeric character. It also must be at least 8 characters long " +
                "with no special symbols";
        String actual = null;
        try {
            clientService.signIn("aleh", "123423532@w", "aleh@mail");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signInInvalidEmailTest() {
        String expected = "The email address is not correct";
        String actual = null;
        try {
            clientService.signIn("aleh", "1234qwer", "alehmail");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signInNullEmailTest() {
        String expected = "The email address is not correct";
        String actual = null;
        try {
            clientService.signIn("aleh", "1234qwer", null);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeUserTest() throws ServiceException {
//        String login = "sand";
//        String password = "1234qwer";
//        String email = "aleh@mail";
//        clientService.signIn(login, password, email);
//        clientService.removeUser(testSessionId, "BPS", password, login, password);
//        Assert.assertNull(innerUser);
    }

    @Test
    public void removeUserInvalidSessionIdTest() throws ServiceException {
//        clientService.removeUser("sadfasd", "BPS;Prior", "1234qwer", "Sam", "1234qwer");
    }

    @Test
    public void getUserTest() throws ServiceException {
        signIn();
        clientService.getUser("sand");
    }

    @Test
    public void getUserInvalidLoginTest() throws ServiceException {
        signIn();
        String expected = "User was not found";
        String actual = null;
        try {
            clientService.getUser("invalid login");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getUserNullLoginTest() throws ServiceException {
        signIn();
        String expected = "User was not found";
        String actual = null;
        try {
            clientService.getUser(null);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logInTest() throws ServiceException {
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        clientService.logIn("sand", "1234qwer");
        Assert.assertNotNull(temporalSession);
        Assert.assertNotNull(testSessionId);
    }

    @Test
    public void logInInvalidLoginTest() throws ServiceException {
        String login = "sand";
        String password = "1234qwer";
        String email = "aleh@mail";
        String expected = "Login or password is not correct";
        String actual = null;
        clientService.signIn(login, password, email);
        User user = clientService.getUser(login);
        Assert.assertNotNull(user);
        try {
            clientService.logIn("invalid", password);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logInNullLoginTest() throws ServiceException {
        String login = "sand";
        String password = "1234qwer";
        String email = "aleh@mail";
        String expected = "Login or password is not correct";
        String actual = null;
        clientService.signIn(login, password, email);
        User user = clientService.getUser(login);
        Assert.assertNotNull(user);
        try {
            clientService.logIn(null, password);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logInInvalidPasswordTest() throws ServiceException {
        String expected = "Login or password is not correct";
        String actual = null;
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        try {
            clientService.logIn("sand", "invalid");
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logInNullPasswordTest() throws ServiceException {
        String expected = "Login or password is not correct";
        String actual = null;
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        try {
            clientService.logIn("sand", null);
        } catch (ServiceException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logOutTest() throws ServiceException {
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        clientService.logIn("sand", "1234qwer");
        clientService.logOut(testSessionId);
        Assert.assertNull(temporalSession);
    }

    @Test
    public void logOutInvalidSessionIdTest() throws ServiceException {
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        clientService.logIn("sand", "1234qwer");
        clientService.logOut("invalid");
        Assert.assertNotNull(temporalSession);
    }

    @Test
    public void logOutNullSessionIdTest() throws ServiceException {
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        clientService.logIn("sand", "1234qwer");
        clientService.logOut(null);
        Assert.assertNotNull(temporalSession);
    }

    @Test
    public void getSessionTest() throws ServiceException {
        signIn();
        User user = clientService.getUser("sand");
        Assert.assertNotNull(user);
        clientService.logIn("sand", "1234qwer");
        // не знаю, как сделать этот тест
//        Assert.assertNotNull(clientService.getSession("123"));
    }

    @Test
    public void getSessionInvalidSessionIdTest() throws ServiceException {
        signIn();
        clientService.logIn("sand", "1234qwer");
        Assert.assertNull(clientService.getSession("invalid"));
    }

    @Test
    public void getSessionNullSessionIdTest() throws ServiceException {
        signIn();
        clientService.logIn("sand", "1234qwer");
        Assert.assertNull(clientService.getSession(null));
    }

    private void signIn() throws ServiceException {
        String login = "sand";
        String password = "1234qwer";
        String email = "aleh@mail";
        clientService.signIn(login, password, email);
    }

}