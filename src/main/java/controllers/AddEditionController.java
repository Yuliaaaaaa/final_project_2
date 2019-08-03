package controllers;

import commonlyUsedStrings.PageLocation;
import converters.StringConverter;
import enums.Category;
import enums.Periodicity;
import factories.CategoryFactory;
import factories.PeriodicityFactory;
import models.Edition;
import services.EditionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 25.07.2019
 * @project publishing
 */
public class AddEditionController implements GetMethodController, PostMethodController {
    private static final EditionService service = EditionService.getEditionService();

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) {
        Object admin = req.getSession().getAttribute("admin");
        if (admin == null)
            return PageLocation.NOT_AUTHORISED;
        return PageLocation.ADD_EDITION;
    }

    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String title = StringConverter.convertToUTF8(req.getParameter("title"));
        String category = req.getParameter("category");
        String periodicity = req.getParameter("periodicity");
        String price = req.getParameter("price");
        String details = StringConverter.convertToUTF8(req.getParameter("details"));
        if(title.isEmpty() || price.isEmpty() || details.isEmpty()) {
            req.setAttribute("fail", true);
            req.setAttribute("title", title);
            req.setAttribute("category", category);
            req.setAttribute("periodicity", periodicity);
            req.setAttribute("price", price);
            req.setAttribute("details", details);
            return PageLocation.ADD_EDITION;
        }
        Double priceNumber = Double.valueOf(price);
        Category categoryEnum = CategoryFactory.getCategory(category);
        Periodicity periodicityEnum = PeriodicityFactory.getPeriodicity(periodicity);
        Edition edition = new Edition(title, categoryEnum, periodicityEnum, details, priceNumber);
        service.add(edition);
        resp.sendRedirect("/adminEditions");
        req.setAttribute("success", true);
        return null;
    }
}
