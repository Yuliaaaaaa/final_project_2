package servlets;

import commonlyUsedStrings.ErrorMessage;
import commonlyUsedStrings.PageLocation;
import exceptionHandling.exceptions.NotAuthorisedException;
import factories.ControllerFactory;
import org.apache.log4j.Logger;

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

    private static Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        try {
            page = ControllerFactory.doGet(req);
        } catch (SQLException e) {
            page = PageLocation.SQL_EXCEPTION;
            logger.error(ErrorMessage.SQL_EXCEPTION);
        } catch (NotAuthorisedException e) {
            page = PageLocation.NOT_AUTHORISED;
        } catch (NullPointerException e) {
            page = PageLocation.NULL_POINTER_EXCEPTION;
            logger.error(ErrorMessage.NULL_POINTER_EXCEPTION);
        }
        if (page != null)
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
            page = ControllerFactory.doPost(req, resp);
        } catch (SQLException e) {
            page = PageLocation.SQL_EXCEPTION;
            logger.error(ErrorMessage.SQL_EXCEPTION);
        }
        if (page != null)
            req.getRequestDispatcher(page)
                    .forward(req, resp);
    }
}
