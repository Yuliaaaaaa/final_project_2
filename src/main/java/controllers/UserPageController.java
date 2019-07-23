package controllers;

import models.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 23.07.2019
 * @project publishing
 */
public class UserPageController {

    public static String doGet(HttpServletRequest req) {
        return "pages/userInfo.jsp";
    }
}
