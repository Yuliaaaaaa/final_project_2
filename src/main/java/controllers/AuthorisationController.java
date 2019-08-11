package controllers;

import commonlyUsedStrings.AdminData;
import commonlyUsedStrings.PageLocation;
import facade.UserFacade;
import dtos.SecureUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 22.07.2019
 * @project publishing
 */
public class AuthorisationController implements GetMethodController, PostMethodController {
    private static UserFacade userFacade = UserFacade.getUserFacade();
    private static Logger logger = Logger.getLogger(AuthorisationController.class);

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) {
        return PageLocation.AUTHORISATION_PAGE;
    }

    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email.isEmpty() || password.isEmpty()) {
            req.setAttribute("emptyField", true);
            return PageLocation.AUTHORISATION_PAGE;
        }
        if(email.equals(AdminData.getLogin()) && password.equals(AdminData.getPassword())){
            req.getSession().setAttribute("user", new SecureUser(true));
            resp.sendRedirect("/adminMain");
            return null;
        }
        SecureUser user = userFacade.checkAuthorizationInfo(email, password);
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
