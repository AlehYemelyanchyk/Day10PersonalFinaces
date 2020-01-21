package by.javatr.finances.dao.factory;

import by.javatr.finances.dao.impl.FileFinancesEntityDAO;
import by.javatr.finances.dao.impl.FileSessionDAO;
import by.javatr.finances.dao.impl.FileUserDAO;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final FileFinancesEntityDAO financesEntityDAO = new FileFinancesEntityDAO();
    private final FileUserDAO userDAO = new FileUserDAO();
    private final FileSessionDAO sessionDAO = new FileSessionDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public FileFinancesEntityDAO getFinancesEntityDAO() {
        return financesEntityDAO;
    }

    public FileUserDAO getUserDAO() {
        return userDAO;
    }

    public FileSessionDAO getSessionDAO() {
        return sessionDAO;
    }
}
