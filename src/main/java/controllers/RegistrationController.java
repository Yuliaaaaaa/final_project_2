package controllers;

import commonlyUsedStrings.PageLocation;
import converters.StringConverter;
import dtos.UserWithPassword;
import models.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class RegistrationController {
    private static final UserService service = UserService.getUserService();
    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    public static String doGet(HttpServletRequest req) {
        return PageLocation.REGISTRATION_PAGE;
    }

    public static String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String firstName = StringConverter.convertToUTF8(req.getParameter("firstName"));
        String lastName = StringConverter.convertToUTF8(req.getParameter("lastName"));
        String birth = req.getParameter("birthDate");
        String sex = req.getParameter("sex");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = StringConverter.convertToUTF8(req.getParameter("email"));
        String password = StringConverter.convertToUTF8(req.getParameter("password"));
        String password2 = StringConverter.convertToUTF8(req.getParameter("password2"));
        if(firstName.isEmpty() || lastName.isEmpty() || birth.isEmpty()
                || sex.isEmpty() || email.isEmpty()
                || password.isEmpty() || password2.isEmpty()) {
            req.setAttribute("emptyFields", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        if(!password.equals(password2)){
            req.setAttribute("passwordsNotEqual", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        Pattern namePattern = Pattern.compile("[a-zA-ZА-Яа-я]+[/-]?[a-zA-ZА-Яа-я]*");
        Matcher matcher = namePattern.matcher(firstName);
        if(!matcher.matches()){
            req.setAttribute("fnWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        matcher = namePattern.matcher(lastName);
        if(!matcher.matches()){
            req.setAttribute("lnWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        Pattern phonePattern = Pattern.compile("[0-9]+");
        matcher = phonePattern.matcher(phoneNumber);
        if(!matcher.matches()){
            req.setAttribute("phWrong", true);
            setAttributes(req, firstName, lastName, birth, sex, phoneNumber, email);
            return PageLocation.REGISTRATION_PAGE;
        }
        Pattern emailPattern = Pattern.compile("[a-zA-ZА-Яа-я0-9_]+[@]{1}[a-zA-ZА-Яа-я]+[/.]{1}[a-zA-ZА-Яа-я]+");
        matcher = emailPattern.matcher(email);
        if(!matcher.matches()){
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
        UserWithPassword user =
                new UserWithPassword(firstName, lastName, birthDate, sex.charAt(0), email, password);
        if (!phoneNumber.isEmpty())
            user.setPhoneNumber(Long.getLong(phoneNumber));
        service.add(user);
        resp.sendRedirect("/authorisation");
        return null;
    }

    private static void setAttributes(HttpServletRequest req, String firstName, String lastName, String birth, String sex, String phoneNumber, String email) {
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("birth", birth);
        req.setAttribute("sex", sex);
        req.setAttribute("phoneNumber", phoneNumber);
        req.setAttribute("email", email);
    }

}

