package application.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Database details
    private static String dbURL;
    private static String dbUser;
    private static String dbPwd;
    private static ConnectionFactory connectionFactory;

    // Constructor
    private ConnectionFactory(String url, String user, String pwd) {
        dbURL = url;
        dbUser = user;
        dbPwd = pwd;
    }

    // Instance of ConnectionFactory
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {

            connectionFactory = new ConnectionFactory("jdbc:mysql://localhost/valu", "cyrill", "valu$3512");
        }
        return connectionFactory;
    }

    // Create database connection
    public static Connection getConnection() throws SQLException {
        if (connectionFactory == null) {
            getInstance();
        }
        return DriverManager.getConnection(dbURL, dbUser, dbPwd);
    }

    // Getters and setters
    public static String getDbURL() {
        return dbURL;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPwd() {
        return dbPwd;
    }
}
