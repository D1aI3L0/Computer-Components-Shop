package org.example.tables;

import org.example.models.Motherboard;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.DatabaseConnection.getConnection;

public class MotherboardDB {


    public void add(Motherboard motherboard) {
        String sql = "INSERT INTO shop.Motherboard (memory_slots, chipset, form_factor) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, motherboard.getMemorySlots());
            stmt.setString(2, motherboard.getChipset());
            stmt.setString(3, motherboard.getFormFactor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Motherboard getById(int id) {
        String sql = "SELECT * FROM shop.Motherboard WHERE id = ?";
        Motherboard motherboard = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                motherboard = new Motherboard(
                        rs.getInt("id"),
                        rs.getInt("memory_slots"),
                        rs.getString("chipset"),
                        rs.getString("form_factor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motherboard;
    }

    public List<Motherboard> getAll() {
        String sql = "SELECT * FROM shop.Motherboard";
        List<Motherboard> motherboards = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                motherboards.add(new Motherboard(
                        rs.getInt("id"),
                        rs.getInt("memory_slots"),
                        rs.getString("chipset"),
                        rs.getString("form_factor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motherboards;
    }

    public void update(Motherboard motherboard) {
        String sql = "UPDATE shop.Motherboard SET memory_slots = ?, chipset = ?, form_factor = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, motherboard.getMemorySlots());
            stmt.setString(2, motherboard.getChipset());
            stmt.setString(3, motherboard.getFormFactor());
            stmt.setInt(4, motherboard.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Motherboard WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}