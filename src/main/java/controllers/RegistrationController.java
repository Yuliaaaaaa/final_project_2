package controllers;

import commonlyUsedStrings.PageLocation;
import converters.StringConverter;
import exceptionHandling.MatchingValidator;
import exceptionHandling.InputDataValidator;
import models.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class RegistrationController implements GetMethodController, PostMethodController {
    private static final UserService service = UserService.getUserService();
    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) {
        return PageLocation.REGISTRATION_PAGE;
    }

    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String firstName = StringConverter.convertToUTF8(req.getParameter("firstName"));
        String lastName = StringConverter.convertToUTF8(req.getParameter("lastName"));
        String birth = req.getParameter("birthDate");
        String sex = req.getParameter("sex");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = StringConverter.convertToUTF8(req.getParameter("email"));
        String password = StringConverter.convertToUTF8(req.getParameter("password"));
        String password2 = StringConverter.convertToUTF8(req.getParameter("password2"));
        if(!InputDataValidator.registrationDataNotEmpty(firstName, lastName, birth, sex, email,
                password, password2)) {
            req.setAttribute("emptyFields", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        if(!InputDataValidator.registrationPasswordsEqual(password, password2)){
            req.setAttribute("passwordsNotEqual", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        if(!MatchingValidator.nameMatches(firstName)){
            req.setAttribute("fnWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        if(!MatchingValidator.nameMatches(lastName)){
            req.setAttribute("lnWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        if(!MatchingValidator.phoneMatches(phoneNumber)){
            req.setAttribute("phWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        if(!MatchingValidator.emailMatches(email)){
            req.setAttribute("emailWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        Date birthDate = null;
        try {
            birthDate = (new SimpleDateFormat("yyyy-MM-dd").parse(birth));
        } catch (ParseException e) {
            logger.error("Birth date can not be parsed");
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        User user =
                new User(firstName, lastName, birthDate, sex.charAt(0), email, password);
        if (!phoneNumber.isEmpty())
            user.setPhoneNumber(Long.getLong(phoneNumber));
        service.add(user);
        resp.sendRedirect("/authorisation");
        return null;
    }

    /**
     * @param req
     * @param firstName
     * @param lastName
     * @param birth
     * @param sex
     * @param phoneNumber
     * @param email
     */
    private static void setAttributes(HttpServletRequest req, String firstName, String lastName, String birth, String sex, String phoneNumber, String email) {
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("birth", birth);
        req.setAttribute("sex", sex);
        req.setAttribute("phoneNumber", phoneNumber);
        req.setAttribute("email", email);
    }

}

