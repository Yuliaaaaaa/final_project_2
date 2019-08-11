package controllers;

import commonlyUsedStrings.ErrorMessage;
import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import exceptionHandling.exceptions.NotAuthorisedException;
import exceptionHandling.validators.AuthorisationValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 23.07.2019
 * @project publishing
 */
public class UserPageController implements GetMethodController {
    private static final Logger logger = Logger.getLogger(UserPageController.class);

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        try {
            if (AuthorisationValidator.userAuthorised(user))
                return PageLocation.USER_INFO;
        } catch (NotAuthorisedException e) {
            return PageLocation.NOT_AUTHORISED;
        }
        logger.error(ErrorMessage.NOT_AUTHORISED);
        return null;
    }
}
