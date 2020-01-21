package by.javatr.finances.dao;

import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public interface UserDAO {

    User get(String login) throws DAOException;

    void save(User user) throws DAOException;

    void delete(String login) throws DAOException;
}
