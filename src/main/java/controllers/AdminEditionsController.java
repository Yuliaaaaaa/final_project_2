package controllers;

import commonlyUsedStrings.ErrorMessage;
import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import exceptionHandling.exceptions.NotAuthorisedException;
import exceptionHandling.validators.AuthorisationValidator;
import models.Edition;
import org.apache.log4j.Logger;
import pagination.EditionsPagination;
import services.EditionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuliia Shcherbakova ON 25.07.2019
 * @project publishing
 */
public class AdminEditionsController implements GetMethodController, PostMethodController {
    private static final EditionService service = EditionService.getEditionService();
    private static final Logger logger = Logger.getLogger(AdminEditionsController.class);

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    public String doGet(HttpServletRequest req) throws SQLException{
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        try {
            if (AuthorisationValidator.adminAuthorised(user)){
                List<Edition> all = service.getAll();
                all = EditionsPagination.getPagination()
                        .getElements(req, all);
                req.setAttribute("editions", all);
                return PageLocation.ADMIN_EDITIONS;
            }
        }
        catch (NotAuthorisedException e) {
            return PageLocation.NOT_AUTHORISED;
        }
        logger.error(ErrorMessage.NOT_AUTHORISED);
        return null;
    }


    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = Integer.valueOf(req.getParameter("id"));
        service.delete(id);
        req.setAttribute("deleted", true);
        return doGet(req);
    }
}
