package controllers;

import models.User;
import org.apache.log4j.Logger;
import repositories.UserRepository;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 22.07.2019
 * @project publishing
 */
public class AuthorisationController {
    private static UserService userService = UserService.getUserService();
    private static Logger logger = Logger.getLogger(AuthorisationController.class);

    public static String doGet(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        return "pages/authorisationPage.jsp";
    }
    public static String doPost(HttpServletRequest req) throws SQLException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email.isEmpty() || password.isEmpty()) {
            req.setAttribute("emptyField", true);
            return "pages/authorisationPage.jsp";
        }
        User user = userService.checkAuthorizationInfo(email, password);
        if(user != null){
            req.getSession()
                    .setAttribute("user", user);
            logger.info("Authorisation success: " + user.getFirstName() + " " + user.getLastName());
            return "pages/userInfo.jsp";
        }
        else{
            req.setAttribute("fail", true);
            return "pages/authorisationPage.jsp";
        }
    }
}
