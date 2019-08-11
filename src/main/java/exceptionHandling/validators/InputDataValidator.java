package exceptionHandling.validators;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class InputDataValidator {

    private InputDataValidator() {
    }

    public static boolean registrationDataNotEmpty
            (String firstName, String lastName, String birth, String sex, String email, String password, String password2) {
        return !(firstName.isEmpty() || lastName.isEmpty() || birth.isEmpty()
                || sex.isEmpty() || email.isEmpty()
                || password.isEmpty() || password2.isEmpty());
    }

    public static boolean registrationPasswordsEqual(String password, String password2) {
        return password.equals(password2);
    }

    public static boolean editionsDataNotEmpty(String title, String price, String details) {
        return !(title.isEmpty() || price.isEmpty() || details.isEmpty());
    }

    public static boolean authorisationDataNotEmpty(String email, String password) {
        return !(email.isEmpty() || password.isEmpty());
    }
}
