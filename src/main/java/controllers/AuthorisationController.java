package controllers;

import commonlyUsedStrings.AdminData;
import commonlyUsedStrings.PageLocation;
import models.Edition;
import models.User;
import org.apache.log4j.Logger;
import repositories.EditionRepository;
import services.Service;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 22.07.2019
 * @project publishing
 */
public class AuthorisationController {
    private static UserService userService = UserService.getUserService();
    private static Logger logger = Logger.getLogger(AuthorisationController.class);

    public static String doGet(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("admin");
        return PageLocation.AUTHORISATION_PAGE;
    }
    public static String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email.isEmpty() || password.isEmpty()) {
            req.setAttribute("emptyField", true);
            return PageLocation.AUTHORISATION_PAGE;
        }
        if(email.equals(AdminData.getLogin()) && password.equals(AdminData.getPassword())){
            resp.sendRedirect("/adminEditions");
            return null;
        }
        User user = userService.checkAuthorizationInfo(email, password);
        if(user != null){
            req.getSession()
                    .setAttribute("user", user);
            logger.info("Authorisation success: " + user.getFirstName() + " " + user.getLastName());
            resp.sendRedirect("/userPage");
            return null;
        }
        else{
            req.setAttribute("fail", true);
            return PageLocation.AUTHORISATION_PAGE;
        }
    }
}
