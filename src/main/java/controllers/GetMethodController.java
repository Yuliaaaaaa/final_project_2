package controllers;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 01.08.2019
 * @project publishing
 */
public interface GetMethodController {
    /**
     * @param req
     * @return
     * @throws SQLException
     */
    String doGet(HttpServletRequest req) throws SQLException;
}
