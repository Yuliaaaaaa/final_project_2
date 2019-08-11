package exceptionHandling.validators;

import dtos.SecureUser;
import exceptionHandling.exceptions.NotAuthorisedException;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class AuthorisationValidator {

    /**
     * @param user
     * @return
     * @throws NotAuthorisedException
     */
    public static boolean userAuthorised(SecureUser user) throws NotAuthorisedException {
        if (user != null) return true;
        throw new NotAuthorisedException();
    }

    /**
     * @param user
     * @return
     * @throws NotAuthorisedException
     */
    public static boolean adminAuthorised(SecureUser user) throws NotAuthorisedException {
        if (user != null && user.isAdmin()) return true;
        throw new NotAuthorisedException();
    }
}
