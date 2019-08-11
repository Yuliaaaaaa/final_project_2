package daos;

import jdbc.ConnectionPool;

import java.sql.Connection;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public interface DAO {

    default Connection receiveConnection(){
        return ConnectionPool.getConnection();
    }
}
