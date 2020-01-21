package by.javatr.finances.dao.impl;

import by.javatr.finances.dao.UserDAO;
import by.javatr.finances.dao.bean.User;
import by.javatr.finances.dao.exception.DAOException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class FileUserDAO implements UserDAO {

    private final static String FILE_NAME = "User_";

    @Override
    public User get(String login) throws DAOException {
        User user = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(
                Paths.get(FILE_NAME + login)))) {
            user = (User) in.readObject();
        } catch (NoSuchFileException e) {
            throw new DAOException("A user with such name does not exist", e);
        } catch (EOFException ignored) {
        } catch (IOException e) {
            throw new DAOException("Can not read the user's file", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Wrong object type", e);
        }
        return user;
    }

    @Override
    public void save(User user) throws DAOException {
        File file = new File((FILE_NAME + user.getLogin()));
        if (file.exists()) {
            throw new DAOException("A user with such login already exists");
        }
        try (ObjectOutputStream out =
                     new ObjectOutputStream(Files.newOutputStream(
                             Paths.get(FILE_NAME + user.getLogin())))) {
            out.writeObject(user);
        } catch (IOException e) {
            throw new DAOException("Can not add the user to the file", e);
        }
    }

    @Override
    public void delete(String login) throws DAOException {
        File file = new File((FILE_NAME + login));
        if (!file.delete()) {
            throw new DAOException("File was not deleted");
        }
    }
}
