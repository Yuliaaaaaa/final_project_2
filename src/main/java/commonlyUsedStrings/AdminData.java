package commonlyUsedStrings;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Yuliia Shcherbakova ON 24.07.2019
 * @project publishing
 */
public class AdminData {
    private static final String LOGIN;
    private static final String PASSWORD;
    private static final Logger logger = Logger.getLogger(AdminData.class);

    private AdminData(){}

    static {
        String temporaryLogin = null;
        String temporaryPassword = null;
        try (FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\adminData.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            temporaryLogin = properties.getProperty("login");
            temporaryPassword = properties.getProperty("password");
        } catch (FileNotFoundException e) {
            logger.error("File with admin data not found");
        } catch (IOException e) {
            logger.error(ErrorMessage.IO_EXCEPTION);
        }
        LOGIN = temporaryLogin;
        PASSWORD = temporaryPassword;
    }

    public static String getLogin() {
        return LOGIN;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
