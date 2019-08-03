package controllers;

import commonlyUsedStrings.PageLocation;

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
        return PageLocation.USER_INFO;
    }
}
