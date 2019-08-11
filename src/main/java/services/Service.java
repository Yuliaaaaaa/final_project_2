package services;

import daos.repositories.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 20.07.2019
 * @project publishing
 */
public class Service<T> {
    protected Repository<T> repository;

    /**
     * @param repository
     */
    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    /**
     * @param item
     * @throws SQLException
     */
    public void add(T item) throws SQLException{
        repository.add(item);
    }

    /**
     * @param id
     * @throws SQLException
     */
    public void delete(int id) throws SQLException{
        repository.delete(id);
    }

    /**
     * @param item
     * @throws SQLException
     */
    public void update(T item) throws SQLException{
        repository.update(item);
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<T> getAll() throws SQLException{
        return repository.getAll();
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public T getOneById(int id) throws SQLException{
        return repository.getOneById(id);
    }
}
