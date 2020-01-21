package by.javatr.finances.dao.impl;

import by.javatr.finances.dao.SessionDAO;
import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;

import java.io.*;

/**
 * @author Aleh Yemelyanchyk on 12/30/2019.
 */
public class FileSessionDAO implements SessionDAO {

    @Override
    public String get(String sessionId) throws DAOException {
        String session = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(sessionId))) {
            String line;
            while ((line = reader.readLine()) != null) {
                session = line;
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("File was not found", e);
        } catch (IOException e) {
            throw new DAOException("Can not read the file", e);
        }
        return session;
    }

    @Override
    public void save(String sessionId, User user) throws DAOException {
        File file = new File((sessionId));
        if (file.exists()) {
            throw new DAOException("A session with such id already exists");
        }
        try (FileWriter writer = new FileWriter(sessionId)) {
            writer.write(user.getLogin() + ";");
            writer.write(user.getPassword() + ";");
            writer.write(user.getEmail());
        } catch (IOException e) {
            throw new DAOException("Can not create session file", e);
        }
    }

    @Override
    public void delete(String sessionId) throws DAOException {
        File file = new File((sessionId));
        if (!file.delete()) {
            throw new DAOException("File was not deleted");
        }
    }
}
