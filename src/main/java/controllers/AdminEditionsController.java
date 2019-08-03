package controllers;

import commonlyUsedStrings.PageLocation;
import models.Edition;
import repositories.EditionRepository;
import services.EditionService;
import services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Object admin = req.getSession().getAttribute("admin");
        if (admin == null)
            return PageLocation.NOT_AUTHORISED;
        List<Edition> all = service.getAll();
        int startIndex = (int) req.getAttribute("startIndex");
        if (startIndex == 0)
            req.setAttribute("start", true);
        if(all.size() - startIndex <= 10)
            req.setAttribute("end", true);
        all = all.stream().skip(startIndex).limit(10).collect(Collectors.toList());
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
