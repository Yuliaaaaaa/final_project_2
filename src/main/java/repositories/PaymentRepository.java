package repositories;

import jdbc.ConnectionPool;
import models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 19.07.2019
 * @project publishing
 */
public class PaymentRepository implements Repository<Payment> {
    /**
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(Payment item) throws SQLException {
        String sqlAdd = "INSERT INTO Payments(user_id, payment_sum, payment_date) " +
                "VALUES (?, ?, ?);";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd);
        preparedStatement.setInt(1, item.getUserId());
        preparedStatement.setDouble(2, item.getPaymentSum());
        preparedStatement.setTimestamp(3, item.getPaymentDate());
        preparedStatement.execute();
        connection.close();
    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        String sqlDelete = "DELETE FROM Payments WHERE payment_id = ?;";
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
    public void update(Payment item) throws SQLException {
        String sqlUpdate = "UPDATE Payments " +
                "SET user_id = ?, payment_sum = ?, payment_date = ? " +
                "WHERE payment_id = ?;";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, item.getUserId());
        preparedStatement.setDouble(2, item.getPaymentSum());
        preparedStatement.setTimestamp(3, item.getPaymentDate());
        preparedStatement.setInt(4, item.getPaymentId());
        preparedStatement.execute();
        connection.close();
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Payment> getAll() throws SQLException {
        String sqlSelect = "SELECT * FROM Payments";
        return query(sqlSelect);
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Payment getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM Payments WHERE payment_id = ?;";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> items = getItems(resultSet);
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
    public List<Payment> getItems(ResultSet resultSet) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            int paymentId = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            double paymentSum = resultSet.getDouble(3);
            Timestamp paymentDate = resultSet.getTimestamp(4);

            Payment payment = new Payment();
            payment.setPaymentId(paymentId);
            payment.setUserId(userId);
            payment.setPaymentSum(paymentSum);
            payment.setPaymentDate(paymentDate);

            payments.add(payment);
        }
        return payments;
    }
}
