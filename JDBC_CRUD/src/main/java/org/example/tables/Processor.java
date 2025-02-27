package org.example.tables;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Processor {


    public void create(int threadsCount, double clockFrequency, double maxFrequency, int cpuCount) {
        String sql = "INSERT INTO shop.Processor (threads_count, clock_frequency, max_frequency, cpu_count) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, threadsCount);
            pstmt.setDouble(2, clockFrequency);
            pstmt.setDouble(3, maxFrequency);
            pstmt.setInt(4, cpuCount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void read(int id) {
        String sql = "SELECT * FROM shop.Processor WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Threads Count: " + rs.getInt("threads_count"));
                System.out.println("Clock Frequency: " + rs.getDouble("clock_frequency"));
                System.out.println("Max Frequency: " + rs.getDouble("max_frequency"));
                System.out.println("CPU Count: " + rs.getInt("cpu_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, int threadsCount, double clockFrequency, double maxFrequency, int cpuCount) {
        String sql = "UPDATE shop.Processor SET threads_count = ?, clock_frequency = ?, max_frequency = ?, cpu_count = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, threadsCount);
            pstmt.setDouble(2, clockFrequency);
            pstmt.setDouble(3, maxFrequency);
            pstmt.setInt(4, cpuCount);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM shop.Processor WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}