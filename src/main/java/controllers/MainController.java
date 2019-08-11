package controllers;

import commonlyUsedStrings.PageLocation;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class MainController implements GetMethodController {
    public String doGet(HttpServletRequest req){
        return PageLocation.MAIN;
    }
}
