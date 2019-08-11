package daos.repositories;

import daos.DAO;
import jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 17.07.2019
 * @project publishing
 */
public interface Repository<T> extends DAO {
    void add(T item) throws SQLException;
    void delete(int id) throws SQLException;
    void update(T item) throws SQLException;
    List<T> getAll() throws SQLException;
    T getOneById(int id) throws SQLException;
    List<T> getItems(ResultSet resultSet) throws SQLException;

    /**
     * @param query
     * @return
     * @throws SQLException
     */
    default List<T> query(String query) throws SQLException {
        Connection connection = receiveConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<T> items = getItems(resultSet);
        connection.close();
        return items;
    }

}
