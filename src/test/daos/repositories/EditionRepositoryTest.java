package daos.repositories;

import commonlyUsedStrings.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class EditionRepositoryTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Spy
    private EditionRepository spyRepository = spy(EditionRepository.class);

    @Before
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(spyRepository.receiveConnection()).thenReturn(connection);
        when(resultSet.next()).thenReturn(false);
    }

    @Test
    public void getAllUnsubscribedEditions() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            spyRepository.getAllUnsubscribedEditions(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }
}