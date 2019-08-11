package jdbc;

import commonlyUsedStrings.ErrorMessage;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author Yuliia Shcherbakova ON 10.08.2019
 * @project publishing
 */
public class ConnectionPoolTest {

    @Test
    public void receivedConnectionShouldNotBeNull(){
        try {
            Connection connection = ConnectionPool.getConnection();
            assertNotNull("Connection was not received", connection);
            connection.close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }
}