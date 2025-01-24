package application.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PasswordJDBCDao implements PasswordDAO {

    // Add User Data to Database
    public static void savePassword(String userUID, String username, String email, String Application, String password) {

        // Generate UUID
        UUID UID = UUID.randomUUID();
        String passwordUID = UID.toString();

        // SQL query to insert
        String sql = "INSERT INTO password (passwordSlotUID, userUID, username, email, AppWebsite, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, passwordUID);
            ps.setString(2, userUID);
            ps.setString(3, username);
            ps.setString(4, email);
            ps.setString(5, Application);
            ps.setString(6, password);

            ps.executeUpdate();
            System.out.println("Password saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
