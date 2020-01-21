package by.javatr.finances.dao;

import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;

/**
 * @author Aleh Yemelyanchyk on 12/30/2019.
 */
public interface SessionDAO {

    String get(String sessionId) throws DAOException;

    void save(String sessionId, User user) throws DAOException;

    void delete(String sessionId) throws DAOException;
}
