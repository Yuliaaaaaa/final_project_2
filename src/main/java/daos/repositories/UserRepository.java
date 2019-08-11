package daos.repositories;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 19.07.2019
 * @project publishing
 */
public class UserRepository implements Repository<User> {

    /**
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(User item) throws SQLException {
        String sqlAdd = "INSERT INTO Users (first_name, last_name, birth_date, sex, phone_number,email,`password`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd);
        preparedStatement.setString(1, item.getFirstName());
        preparedStatement.setString(2, item.getLastName());
        preparedStatement.setDate(3, new java.sql.Date(item.getBirthDate().getTime()));
        preparedStatement.setString(4, item.getSex()+"");
        if(item.getPhoneNumber() != null)
            preparedStatement.setLong(5, item.getPhoneNumber());
        else
            preparedStatement.setString(5,null);
        preparedStatement.setString(6, item.getEmail());
        preparedStatement.setString(7, item.getPassword());
        preparedStatement.execute();
        connection.close();
    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        String sqlDelete = "DELETE FROM Users WHERE user_id = ?;";
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
    public void update(User item) throws SQLException {
        String sqlUpdate = "UPDATE Users " +
                "SET first_name = ?, last_name = ?, birth_date = ?, sex = ?, phone_number = ?, email = ? " +
                "WHERE user_id = ?;";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        preparedStatement.setString(1, item.getFirstName());
        preparedStatement.setString(2, item.getLastName());
        preparedStatement.setDate(3, new java.sql.Date(item.getBirthDate().getTime()));
        preparedStatement.setString(4, item.getSex()+"");
        if(item.getPhoneNumber() != null)
            preparedStatement.setLong(5, item.getPhoneNumber());
        else
            preparedStatement.setString(5,null);
        preparedStatement.setString(6, item.getEmail());
        preparedStatement.setInt(7, item.getUserId());
        preparedStatement.execute();
        connection.close();
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> getAll() throws SQLException {
        String sqlSelect = "SELECT * FROM Users;";
        return query(sqlSelect);
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public User getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM Users WHERE user_id = ?;";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> items = getItems(resultSet);
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
    public List<User> getItems(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            Date birthDate = resultSet.getDate(4);
            char sex = resultSet.getString(5).charAt(0);
            Long phoneNumber = resultSet.getLong(6);
            String email = resultSet.getString(7);
            String password = resultSet.getString(8);

            User user = new User();
            user.setUserId(userId);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthDate(birthDate);
            user.setSex(sex);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setPassword(password);

            users.add(user);
        }
        return users;
    }

    public User checkAuthorizationInfo(String email, String password) throws SQLException {
        String sqlSelect = "SELECT * FROM Users WHERE email= ? AND `password`= ?;";
        Connection connection = receiveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = getItems(resultSet);
        connection.close();
        if (users.size() != 0)
            return users.get(0);
         else return null;
    }

}
