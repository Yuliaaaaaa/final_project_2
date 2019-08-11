package daos.repositories;

import commonlyUsedStrings.ErrorMessage;
import enums.Category;
import enums.Periodicity;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Yuliia Shcherbakova ON 10.08.2019
 * @project publishing
 */

@RunWith(Parameterized.class)
public class RepositoryTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @Parameterized.Parameter(0)
    public Repository repository;
    @Parameterized.Parameter(1)
    public Object model;

    @Spy
    public Repository spyRepository;

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> getRepositoriesWithModels() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{new EditionRepository(),
                new Edition("", Category.AGRICULTURE, Periodicity.DAILY, "", 0)});
        data.add(new Object[]{new PaymentRepository(), new Payment()});
        data.add(new Object[]{new PaymentDetailsRepository(), new PaymentDetail()});
        data.add(new Object[]{new SubscriptionRepository(), new Subscription()});
        data.add(new Object[]{new UserRepository(),
                new User("", "", Date.from(Instant.now()), '1', "", "")});
        return data;
    }


    @Before
    public void rules() throws SQLException {
        spyRepository = spy(repository);
        MockitoAnnotations.initMocks(this);
        when(spyRepository.receiveConnection()).thenReturn(connection);
        when(resultSet.next()).thenReturn(false);
    }

    @Test
    public void add() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            spyRepository.add(model);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }

    @Test
    public void delete() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            spyRepository.delete(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }

    @Test
    public void update() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            spyRepository.update(model);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }

    @Test
    public void getAll() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            spyRepository.getAll();
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }

    @Test
    public void getOneById() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            spyRepository.getOneById(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }


    @Test
    public void getItems() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            spyRepository.getItems(resultSet);
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }

    @Test
    public void query() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            spyRepository.query("");
            verify(connection).close();
        } catch (SQLException e) {
            fail(ErrorMessage.SQL_EXCEPTION);
        }
    }
}