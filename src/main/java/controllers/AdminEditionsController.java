package controllers;

import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import models.Edition;
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

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    public String doGet(HttpServletRequest req) throws SQLException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if (user == null || !user.isAdmin())
            return PageLocation.NOT_AUTHORISED;
        List<Edition> all = service.getAll();
        all = EditionsPagination.getPagination()
                .getElements(req, all);
        req.setAttribute("editions", all);
        return PageLocation.ADMIN_EDITIONS;
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
