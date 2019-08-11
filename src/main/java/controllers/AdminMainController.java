package controllers;

import commonlyUsedStrings.ErrorMessage;
import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import exceptionHandling.exceptions.NotAuthorisedException;
import exceptionHandling.validators.AuthorisationValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class AdminMainController implements GetMethodController {
    private static final Logger logger = Logger.getLogger(AdminMainController.class);

    public String doGet(HttpServletRequest req) throws NotAuthorisedException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
            if (AuthorisationValidator.adminAuthorised(user))
                return PageLocation.ADMIN_MAIN;
        logger.error(ErrorMessage.NOT_AUTHORISED);
        return null;
    }
}
