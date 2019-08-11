package daos.transactions;

import commonlyUsedStrings.ErrorMessage;
import daos.repositories.SubscriptionRepository;
import enums.Category;
import enums.Periodicity;
import models.Edition;
import models.Payment;
import models.Subscription;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class PaymentTransactionTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @Spy
    private PaymentTransaction transaction = spy(PaymentTransaction.class);

    @Before
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(transaction.receiveConnection()).thenReturn(connection);
        when(resultSet.next()).thenReturn(false);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
    }

    @Test
    public void payImmediately() {
        try {
            Subscription subscription = new Subscription();
            subscription.
                    setEdition(new Edition("", Category.AGRICULTURE, Periodicity.DAILY, "", 0));
            transaction.payImmediately(subscription, new Payment());
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }

    @Test
    public void payFromCart() {
        try {
            transaction.payFromCart(new ArrayList<>(), new Payment());
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }
}