package controllers;

import commonlyUsedStrings.PageLocation;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class MainController implements GetMethodController {
    public String doGet(HttpServletRequest req){
        return PageLocation.MAIN;
    }
}
