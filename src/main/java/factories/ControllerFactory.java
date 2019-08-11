package factories;

import commonlyUsedStrings.PageLocation;
import controllers.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuliia Shcherbakova ON 08.07.2019
 * @project publishing
 */
public class ControllerFactory {
    private static Map<String, GetMethodController> getMethodControllerMap = new HashMap<>();
    private static Map<String, PostMethodController> postMethodControllerMap = new HashMap<>();

    static {
        getMethodControllerMap.put("AuthorisationController", new AuthorisationController());
        getMethodControllerMap.put("UserPageController", new UserPageController());
        getMethodControllerMap.put("AdminEditionsController", new AdminEditionsController());
        getMethodControllerMap.put("AdminPaymentsController", new AdminPaymentsController());
        getMethodControllerMap.put("AddEditionController", new AddEditionController());
        getMethodControllerMap.put("RegistrationController", new RegistrationController());
        getMethodControllerMap.put("SubscribeController", new SubscribeController());
        getMethodControllerMap.put("CartController", new CartController());
        getMethodControllerMap.put("MySubscriptionsController", new MySubscriptionsController());

        postMethodControllerMap.put("AuthorisationController", new AuthorisationController());
        postMethodControllerMap.put("AdminEditionsController", new AdminEditionsController());
        postMethodControllerMap.put("AddEditionController", new AddEditionController());
        postMethodControllerMap.put("RegistrationController", new RegistrationController());
        postMethodControllerMap.put("SubscribeController", new SubscribeController());
        postMethodControllerMap.put("CartController", new CartController());
        postMethodControllerMap.put("MySubscriptionsController", new MySubscriptionsController());
    }

    /**
     * @param req
     * @return
     */
    public static String doGet(HttpServletRequest req) throws SQLException {
        String requestURI = req.getRequestURI();
        GetMethodController controller = null;
        switch (requestURI) {
            case ("/authorisation"): {
                controller = getMethodControllerMap.get("AuthorisationController");
                break;
            }
            case ("/userPage"): {
                controller = getMethodControllerMap.get("UserPageController");
                break;
            }
            case ("/adminEditions"): {
                controller = getMethodControllerMap.get("AdminEditionsController");
                break;
            }
            case ("/adminPayments"): {
                controller = getMethodControllerMap.get("AdminPaymentsController");
                break;
            }
            case ("/addEdition"): {
                controller = getMethodControllerMap.get("AddEditionController");
                break;
            }
            case ("/registration"): {
                controller = getMethodControllerMap.get("RegistrationController");
                break;
            }
            case ("/subscribe"): {
                controller = getMethodControllerMap.get("SubscribeController");
                break;
            }
            case ("/cart"): {
                controller = getMethodControllerMap.get("CartController");
                break;
            }
            case ("/mySubscriptions"): {
                controller = getMethodControllerMap.get("MySubscriptionsController");
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
                controller = postMethodControllerMap.get("AuthorisationController");
                break;
            }
            case ("/addEdition"): {
                controller = postMethodControllerMap.get("AddEditionController");
                break;
            }
            case ("/adminEditions"): {
                controller = postMethodControllerMap.get("AdminEditionsController");
                break;
            }
            case ("/registration"): {
                controller = postMethodControllerMap.get("RegistrationController");
                break;
            }
            case ("/subscribe"): {
                controller = postMethodControllerMap.get("SubscribeController");
                break;
            }
            case ("/cart"): {
                controller = postMethodControllerMap.get("CartController");
                break;
            }
            case ("/mySubscriptions"): {
                controller = postMethodControllerMap.get("MySubscriptionsController");
                break;
            }
        }
        return (controller != null) ? controller.doPost(req, resp) : PageLocation.PAGE_NOT_FOUND;
    }
}
