package by.javatr.finances.dao;

import by.javatr.finances.dao.bean.FinancesEntity;
import by.javatr.finances.dao.exception.DAOException;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public interface FinancesEntityDAO {
    FinancesEntity get(String id, String entityName) throws DAOException;

    void save(String id, FinancesEntity entity) throws DAOException;

    void delete(String id, String entityName) throws DAOException;
}
