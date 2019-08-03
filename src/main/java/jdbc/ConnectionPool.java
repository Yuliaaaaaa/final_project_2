package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Yuliia Shcherbakova ON 18.07.2019
 * @project publishing
 */
public class ConnectionPool {

    private static ConnectionPool connectionPool;
    private static BasicDataSource basicDataSource;
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool(){

    }

    /**
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(){
        if(connectionPool == null){
            synchronized (ConnectionPool.class) {
                if(connectionPool == null){
                    Properties properties = new Properties();
                    try(FileInputStream fileInputStream =
                                new FileInputStream("src\\main\\resources\\database.properties")){
                        properties.load(fileInputStream);
                        String driverClassName = properties.getProperty("driverClassName");
                        Class.forName(driverClassName);
                        basicDataSource = new BasicDataSource();
                        basicDataSource.setDriverClassName(driverClassName);
                        basicDataSource.setUrl(properties.getProperty("url"));
                        basicDataSource.setUsername(properties.getProperty("username"));
                        basicDataSource.setPassword(properties.getProperty("password"));
                        basicDataSource.setMinIdle(Integer.valueOf(properties.getProperty("minIdle")));
                        basicDataSource.setMaxIdle(Integer.valueOf(properties.getProperty("maxIdle")));
                        basicDataSource.setMaxTotal(Integer.valueOf(properties.getProperty("maxTotal")));
                        basicDataSource.setInitialSize(Integer.valueOf(properties.getProperty("initialSize")));
                        connectionPool = new ConnectionPool();
                        logger.info("Connection Pool created!");
                    } catch (FileNotFoundException e) {
                        logger.error("Connection pool not created! File not found!");
                    } catch (IOException e) {
                        logger.error("Connection pool not created! I/O Exception!");
                    } catch (ClassNotFoundException e) {
                        logger.error("Driver not found!");
                    }
                }
            }
        }
        try {
            Connection connection = basicDataSource.getConnection();
            logger.info("Connection received!");
            return connection;
        } catch (SQLException e) {
            logger.error("Connection not received! SQL error!");
            return null;
        }
    }


}
