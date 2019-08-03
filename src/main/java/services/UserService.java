package services;

import models.User;
import repositories.UserRepository;

import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 22.07.2019
 * @project publishing
 */
public class UserService extends Service<User> {

    private static final UserService userService = new UserService();

    /**
     *
     */
    private UserService() {
        super(new UserRepository());
    }

    /**
     * @return
     */
    public static UserService getUserService() {
        return userService;
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public User checkAuthorizationInfo(String email, String password) throws SQLException {
        return ((UserRepository)repository).
                checkAuthorizationInfo(email, password);
    }

    /**
     * @param user
     * @throws SQLException
     */
    public void add(User user) throws SQLException {
        ((UserRepository)repository)
                .add(user);
    }
}
