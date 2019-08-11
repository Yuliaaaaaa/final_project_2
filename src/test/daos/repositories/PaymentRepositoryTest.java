package daos.repositories;

import commonlyUsedStrings.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class PaymentRepositoryTest {

    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @Spy
    private PaymentRepository spyRepository = spy(PaymentRepository.class);

    @Before
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(spyRepository.receiveConnection()).thenReturn(connection);
        when(resultSet.next()).thenReturn(false);
    }

    @Test
    public void getLast() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            spyRepository.getLast(connection);
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }
}