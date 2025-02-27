package org.example.tables;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Motherboard {

    public void create(int memorySlots, String chipset, String formFactor) {
        String sql = "INSERT INTO shop.Motherboard (memory_slots, chipset, form_factor) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memorySlots);
            pstmt.setString(2, chipset);
            pstmt.setString(3, formFactor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void read(int id) {
        String sql = "SELECT * FROM shop.Motherboard WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Memory Slots: " + rs.getInt("memory_slots"));
                System.out.println("Chipset: " + rs.getString("chipset"));
                System.out.println("Form Factor: " + rs.getString("form_factor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, int memorySlots, String chipset, String formFactor) {
        String sql = "UPDATE shop.Motherboard SET memory_slots = ?, chipset = ?, form_factor = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memorySlots);
            pstmt.setString(2, chipset);
            pstmt.setString(3, formFactor);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Motherboard WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}