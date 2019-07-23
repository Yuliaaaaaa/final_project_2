package controllers;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 08.07.2019
 * @project publishing
 */
public class FrontController {
    /**
     * @param req
     * @return
     */
    public static String doGet(HttpServletRequest req){
        String requestURI = req.getRequestURI();
        switch (requestURI){
            case ("/authorisation"):{
                return AuthorisationController.doGet(req);
            }
            case ("/userPage") : {
                return UserPageController.doGet(req);
            }
        }
        return "errorPages/pageNotFound.jsp";
    }

    /**
     * @param req
     * @return
     */
    public static String doPost(HttpServletRequest req) throws SQLException {
        String requestURI = req.getRequestURI();
        switch (requestURI) {
            case ("/authorisation"): {
                return AuthorisationController.doPost(req);
            }
        }
        return "errorPages/pageNotFound.jsp";
    }
}
