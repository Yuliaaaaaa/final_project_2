package daos.repositories;

import commonlyUsedStrings.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class PaymentDetailsRepositoryTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Spy
    private PaymentDetailsRepository spyRepository = spy(PaymentDetailsRepository.class);

    @Before
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(spyRepository.receiveConnection()).thenReturn(connection);
        when(resultSet.next()).thenReturn(false);
    }

    @Test
    public void getAllByPaymentId() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            spyRepository.getAllByPaymentId(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }
}