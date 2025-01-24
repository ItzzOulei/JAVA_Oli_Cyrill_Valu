package application.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class UserJDBCDao implements UserDao {

    // Get user password by UUID
    public static String getUserPassword(String UUID) {
        // SQL Query to get password hash by UUID
        String getPassSQL = "SELECT passwordHash FROM user WHERE userUID = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(getPassSQL)) {

            checkStmt.setString(1, UUID);

            try (var resultSet = checkStmt.executeQuery()) {
                if (resultSet.next()) {
                    // Return the password hash
                    return resultSet.getString("passwordHash");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Get logged-in user's UUID
    public static String getUserUID(String email, String password) {

        // SQL Query to get UUID
        String getUuidSQL = "SELECT userUID FROM user WHERE email = ? AND passwordHash = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(getUuidSQL)) {

            checkStmt.setString(1, email);
            checkStmt.setString(2, password);

            try (var resultSet = checkStmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("userUID");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no match is found
    }


    // Check if email and password are matching
    public static boolean checkEmailAndPassword(String email, String password) {

        // SQL Query to check if password and email are found
        String checkEmlAndPwdSQL = "SELECT COUNT(*) FROM user WHERE email = ? AND passwordHash = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkEmlAndPwdSQL)) {

            // Check if the email already exists
            checkStmt.setString(1, email);
            checkStmt.setString(2, password);
            var resultSet = checkStmt.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check Email
    public static boolean checkEmailRegistered(String email){

        // SQL query to check email
        String checkEmailSQL = "SELECT COUNT(*) FROM user WHERE email = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkEmailSQL)) {

            // Check if the email already exists
            checkStmt.setString(1, email);
            var resultSet = checkStmt.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Add User Data to Database
    public static void saveData(String username, String email, String password) {

        // Generate UUID
        UUID UID = UUID.randomUUID();
        String userUID = UID.toString();

        // SQL query to insert
        String sql = "INSERT INTO user (userUID, username, email, passwordHash) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userUID);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, password);

            ps.executeUpdate();
            System.out.println("User saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methods for Password by UUID
    public static String getUsername(String UUID){

        // SQL Query to get UUID
        String getUsernameSQL = "SELECT username FROM user WHERE userUID = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(getUsernameSQL)) {

            checkStmt.setString(1, UUID);

            try (var resultSet = checkStmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
