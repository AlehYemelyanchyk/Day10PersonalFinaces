package by.javatr.finances.dao.impl;

import by.javatr.finances.dao.FinancesEntityDAO;
import by.javatr.finances.dao.bean.FinancesEntity;
import by.javatr.finances.dao.exception.DAOException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class FileFinancesEntityDAO implements FinancesEntityDAO {

    @Override
    public FinancesEntity get(String id, String entityName) throws DAOException {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(
                Paths.get(id + entityName)))) {
            return (FinancesEntity) in.readObject();
        } catch (FileNotFoundException e) {
            throw new DAOException("File was not found", e);
        } catch (IOException e) {
            throw new DAOException("Can not read the file", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Wrong object type", e);
        }
    }

    @Override
    public void save(String id, FinancesEntity entity) throws DAOException {
        File entityFile = new File(id + entity.getName());
        if (entityFile.exists()) {
            throw new DAOException("An entity with such name already exists");
        }
        try (ObjectOutputStream out =
                     new ObjectOutputStream(Files.newOutputStream(
                             Paths.get(id + entity.getName())))) {
            out.writeObject(entity);
        } catch (IOException e) {
            throw new DAOException("Can not add the entity to the file", e);
        }
    }

    @Override
    public void delete(String id, String entityName) throws DAOException {
        File file = new File(id + entityName);
        if (!file.delete()) {
            throw new DAOException("File was not deleted");
        }
    }
}
