package factories;

import commonlyUsedStrings.PageLocation;
import controllers.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 08.07.2019
 * @project publishing
 */
public class ControllerFactory {
    /**
     * @param req
     * @return
     */
    public static String doGet(HttpServletRequest req) throws SQLException {
        String requestURI = req.getRequestURI();
        GetMethodController controller = null;
        switch (requestURI){
            case ("/authorisation"):{
                controller = new AuthorisationController();
                break;
            }
            case ("/userPage") : {
                controller = new UserPageController();
                break;
            }
            case ("/adminEditions") : {
                controller = new AdminEditionsController();
                break;
            }
            case ("/adminPayments") : {
                controller = new AdminPaymentsController();
                break;
            }
            case ("/addEdition") : {
                controller = new AddEditionController();
                break;
            }
            case ("/registration") : {
                controller = new RegistrationController();
                break;
            }
            case ("/subscribe") : {
                controller = new SubscribeController();
                break;
            }
        }
        return (controller != null) ? controller.doGet(req) : PageLocation.PAGE_NOT_FOUND;
    }

    /**
     * @param req
     * @return
     */
    public static String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String requestURI = req.getRequestURI();
        PostMethodController controller = null;
        switch (requestURI) {
            case ("/authorisation"): {
                controller = new AuthorisationController();
                break;
            }
            case ("/addEdition") : {
                controller = new AddEditionController();
                break;
            }
            case ("/adminEditions") : {
                controller = new AdminEditionsController();
                break;
            }
            case ("/registration") : {
                controller = new RegistrationController();
                break;
            }
            case ("/subscribe") : {
                controller = new SubscribeController();
                break;
            }
        }
        return (controller != null) ? controller.doPost(req, resp) : PageLocation.PAGE_NOT_FOUND;
    }
}
