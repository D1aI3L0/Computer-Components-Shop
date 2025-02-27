package org.example.tables;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraphicCard {

    public void create(int gpuCount, double gpuFrequency, int memoryCount, double memoryFrequency) {
        String sql = "INSERT INTO shop.Graphic_Card (gpu_count, gpu_frequency, memory_count, memory_frequency) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gpuCount);
            pstmt.setDouble(2, gpuFrequency);
            pstmt.setInt(3, memoryCount);
            pstmt.setDouble(4, memoryFrequency);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void read(int id) {
        String sql = "SELECT * FROM shop.Graphic_Card WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("GPU Count: " + rs.getInt("gpu_count"));
                System.out.println("GPU Frequency: " + rs.getDouble("gpu_frequency"));
                System.out.println("Memory Count: " + rs.getInt("memory_count"));
                System.out.println("Memory Frequency: " + rs.getDouble("memory_frequency"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, int gpuCount, double gpuFrequency, int memoryCount, double memoryFrequency) {
        String sql = "UPDATE shop.Graphic_Card SET gpu_count = ?, gpu_frequency = ?, memory_count = ?, memory_frequency = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gpuCount);
            pstmt.setDouble(2, gpuFrequency);
            pstmt.setInt(3, memoryCount);
            pstmt.setDouble(4, memoryFrequency);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Graphic_Card WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}