package com.bloodbank.service;

import com.bloodbank.model.Donor;
import com.bloodbank.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DonorService {
    public boolean registerDonor(Donor donor) {
        String sql = "INSERT INTO donors (name, age, blood_group, contact, city) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donor.getName());
            stmt.setInt(2, donor.getAge());
            stmt.setString(3, donor.getBloodGroup());
            stmt.setString(4, donor.getContact());
            stmt.setString(5, donor.getCity());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
