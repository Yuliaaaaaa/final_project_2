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

    private UserService() {
        super(new UserRepository());
    }

    public static UserService getUserService() {
        return userService;
    }

    public User checkAuthorizationInfo(String email, String password) throws SQLException {
        return ((UserRepository)repository).
                checkAuthorizationInfo(email, password);
    }
}
