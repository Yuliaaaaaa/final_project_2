package controllers;

import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 23.07.2019
 * @project publishing
 */
public class UserPageController implements GetMethodController {

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if(user == null)
            return PageLocation.NOT_AUTHORISED;
        return PageLocation.USER_INFO;
    }
}
