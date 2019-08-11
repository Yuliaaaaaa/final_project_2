package controllers;

import commonlyUsedStrings.PageLocation;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class SignOutController implements GetMethodController {

    public String doGet(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        return PageLocation.AUTHORISATION_PAGE;
    }
}
