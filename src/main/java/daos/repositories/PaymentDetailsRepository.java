package daos.repositories;

import annotations.NonSecure;
import models.PaymentDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 19.07.2019
 * @project publishing
 */
public class PaymentDetailsRepository implements Repository<PaymentDetail> {
    /**
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(PaymentDetail item) throws SQLException {
        Connection connection = receiveConnection();
        add(item, connection);
        connection.close();
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @NonSecure
    public void add(PaymentDetail item, Connection connection) throws SQLException {
        String sqlAdd = "INSERT INTO `Payments Details`(payment_id, subscription_id) " +
                "VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd);
        preparedStatement.setInt(1, item.getPaymentId());
        preparedStatement.setInt(2, item.getSubscriptionId());
        preparedStatement.execute();
    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        String sqlDelete = "DELETE FROM `Payments details` WHERE details_id = ?;";
        Connection connection = receiveConnection();
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
    public void update(PaymentDetail item) throws SQLException {
        String sqlUpdate = "UPDATE `Payments Details` " +
                "SET payment_id = ?, subscription_id = ? " +
                "WHERE details_id = ?;";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, item.getPaymentId());
        preparedStatement.setInt(2, item.getSubscriptionId());
        preparedStatement.setInt(3, item.getDetailsId());
        preparedStatement.execute();
        connection.close();
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<PaymentDetail> getAll() throws SQLException {
        String sqlSelect = "SELECT * FROM `Payments details`";
        return query(sqlSelect);
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public PaymentDetail getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM `Payments details` WHERE details_id = ?;";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PaymentDetail> items = getItems(resultSet);
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
    public List<PaymentDetail> getItems(ResultSet resultSet) throws SQLException {
        List<PaymentDetail> paymentDetails = new ArrayList<>();
        while (resultSet.next()) {
            int detailsId = resultSet.getInt(1);
            int paymentId = resultSet.getInt(2);
            int subscriptionId = resultSet.getInt(3);

            PaymentDetail paymentDetail = new PaymentDetail();
            paymentDetail.setDetailsId(detailsId);
            paymentDetail.setPaymentId(paymentId);
            paymentDetail.setSubscriptionId(subscriptionId);

            paymentDetails.add(paymentDetail);
        }
        return paymentDetails;
    }

    public List<PaymentDetail> getAllByPaymentId(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM `Payments details` WHERE payment_id = ?;";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PaymentDetail> items = getItems(resultSet);
        connection.close();
        return items;
    }
}
