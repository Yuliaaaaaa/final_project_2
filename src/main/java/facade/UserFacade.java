package facade;

import models.User;
import dtos.SecureUser;
import services.UserService;

import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 * @project publishing
 */
public class UserFacade {
    private static final UserFacade userFacade = new UserFacade();
    private final UserService userService = UserService.getUserService();

    private UserFacade() {
    }

    public static UserFacade getUserFacade() {
        return userFacade;
    }

    private SecureUser getSecureUserDto(User user) {
        SecureUser secureUser = new SecureUser();
        secureUser.setUserId(user.getUserId());
        secureUser.setFirstName(user.getFirstName());
        secureUser.setLastName(user.getLastName());
        secureUser.setBirthDate(user.getBirthDate());
        secureUser.setSex(user.getSex());
        secureUser.setEmail(user.getEmail());
        secureUser.setPhoneNumber(user.getPhoneNumber());
        return secureUser;
    }

    public SecureUser checkAuthorizationInfo(String email, String password) throws SQLException {
        User user = userService.checkAuthorizationInfo(email, password);
        return getSecureUserDto(user);
    }

}
