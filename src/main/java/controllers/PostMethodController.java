package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 01.08.2019
 * @project publishing
 */
public interface PostMethodController {
    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException;
}
