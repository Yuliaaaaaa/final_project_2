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
    private static String login;
    private static String password;
    private static final Logger logger = Logger.getLogger(AdminData.class);

    private AdminData(){}

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\adminData.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            login = properties.getProperty("login");
            password = properties.getProperty("password");
        } catch (FileNotFoundException e) {
            logger.error("File with admin data not found");
        } catch (IOException e) {
            logger.error("I/O Exception occurred!");
        }
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
