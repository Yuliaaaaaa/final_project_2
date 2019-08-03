package repositories;

import jdbc.ConnectionPool;
import models.Payment;
import models.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 19.07.2019
 * @project publishing
 */
public class SubscriptionRepository implements Repository<Subscription> {
    /**
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(Subscription item) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        add(item, connection);
        connection.close();
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    public void add(Subscription item, Connection connection) throws SQLException {
        String sqlAdd = "INSERT INTO Subscriptions(user_id, edition_id, issues_quantity,order_date, is_paid) " +
                "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd);
        preparedStatement.setInt(1, item.getUserId());
        preparedStatement.setInt(2, item.getEditionId());
        preparedStatement.setInt(3, item.getIssuesQuantity());
        preparedStatement.setTimestamp(4, item.getOrderDate(), Calendar.getInstance());
        preparedStatement.setBoolean(5, item.isPaid());
        preparedStatement.execute();
    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        String sqlDelete = "DELETE FROM Subscriptions WHERE subscription_id = ?;;";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        connection.close();
    }

    /**
     * @param item
     * @throws SQLException
     */
    @Override
    public void update(Subscription item) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        update(item, connection);
        connection.close();
    }

    public void update(Subscription item, Connection connection) throws SQLException {
        String sqlUpdate = "UPDATE Subscriptions " +
                "SET issues_quantity = ?, order_date = ?, is_paid = ? " +
                "WHERE subscription_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, item.getIssuesQuantity());
        preparedStatement.setTimestamp(2, item.getOrderDate(), Calendar.getInstance());
        preparedStatement.setBoolean(3, item.isPaid());
        preparedStatement.setInt(4, item.getSubscriptionId());
        preparedStatement.execute();
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Subscription> getAll() throws SQLException {
        String sqlSelect = "SELECT * FROM Subscriptions;";
        return query(sqlSelect);
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Subscription getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM Subscriptions WHERE subscription_id = ?;";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Subscription> items = getItems(resultSet);
        connection.close();
        if(items.size() != 0) return items.get(0);
        else return null;
    }

    /**
     * @param resultSet
     * @return
     * @throws SQLException
     */
    @Override
    public List<Subscription> getItems(ResultSet resultSet) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        while (resultSet.next()) {
            int subscriptionId = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            int editionId = resultSet.getInt(3);
            int issuesQuantity = resultSet.getInt(4);
            Timestamp orderDate = resultSet.getTimestamp(5, Calendar.getInstance());
            boolean isPaid = resultSet.getBoolean(6);

            Subscription subscription = new Subscription();
            subscription.setSubscriptionId(subscriptionId);
            subscription.setUserId(userId);
            subscription.setEditionId(editionId);
            subscription.setIssuesQuantity(issuesQuantity);
            subscription.setOrderDate(orderDate);
            subscription.setPaid(isPaid);

            subscriptions.add(subscription);
        }
        return subscriptions;
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    public Subscription getLast(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM subscriptions WHERE subscription_id = " +
                "(SELECT MAX(subscription_id) FROM subscriptions);";
        List<Subscription> items = query(sqlSelect);
        if(items.size() != 0) return items.get(0);
        else return null;
    }

    public List<Subscription> getAllUnpaidForUser(int userId) throws SQLException {
        String sqlSelect = "SELECT * FROM Subscriptions WHERE user_id = ? AND is_paid = 0;";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Subscription> items = getItems(resultSet);
        connection.close();
        return items;
    }

}
