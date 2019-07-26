package controllers;

import commonlyUsedStrings.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public static String doGet(HttpServletRequest req) throws SQLException {
        String requestURI = req.getRequestURI();
        switch (requestURI){
            case ("/authorisation"):{
                return AuthorisationController.doGet(req);
            }
            case ("/userPage") : {
                return UserPageController.doGet(req);
            }
            case ("/adminEditions") : {
                return AdminEditionsController.doGet(req);
            }
            case ("/addEdition") : {
                return AddEditionController.doGet(req);
            }
        }
        return PageLocation.PAGE_NOT_FOUND;
    }

    /**
     * @param req
     * @return
     */
    public static String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String requestURI = req.getRequestURI();
        switch (requestURI) {
            case ("/authorisation"): {
                return AuthorisationController.doPost(req, resp);
            }
            case ("/addEdition") : {
                return AddEditionController.doPost(req, resp);
            }
            case ("/adminEditions") : {
                return AdminEditionsController.doPost(req, resp);
            }
        }
        return PageLocation.PAGE_NOT_FOUND;
    }
}
