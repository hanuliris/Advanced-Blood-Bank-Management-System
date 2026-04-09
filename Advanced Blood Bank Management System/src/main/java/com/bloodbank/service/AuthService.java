package com.bloodbank.service;

import com.bloodbank.model.User;
import com.bloodbank.util.DBConnection;
import java.sql.*;

public class AuthService {
    public boolean validateLogin(String username, String password, String role) {
        String sql = "SELECT password FROM users WHERE username=? AND role=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, role);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return storedHash.equals(util.PasswordHasher.hash(password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
