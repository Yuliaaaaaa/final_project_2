package servlets;

import controllers.FrontController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 08.07.2019
 * @project publishing
 */
public class DispatcherServlet extends HttpServlet {
    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = FrontController.doGet(req);
        req.getRequestDispatcher(page)
                .forward(req, resp);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        try {
            page = FrontController.doPost(req);
        } catch (SQLException e) {
            page = "errorPages/SQLException.jsp";
        }
        req.getRequestDispatcher(page)
                .forward(req, resp);
    }
}
