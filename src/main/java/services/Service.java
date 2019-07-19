package services;

import repositories.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 20.07.2019
 * @project publishing
 */
public class Service<T> {
    private Repository<T> repository;

    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    public void add(T item) throws SQLException{
        repository.add(item);
    }
    public void delete(T item) throws SQLException{
        repository.delete(item);
    }
    public void update(T item) throws SQLException{
        repository.update(item);
    }
    public List<T> getAll() throws SQLException{
        return repository.getAll();
    }
    public T getOneById(int id) throws SQLException{
        return repository.getOneById(id);
    }
}
