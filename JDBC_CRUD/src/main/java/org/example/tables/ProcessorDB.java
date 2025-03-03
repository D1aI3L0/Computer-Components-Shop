package org.example.tables;

import org.example.DatabaseConnection;
import org.example.models.Processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessorDB {

    public void add(Processor processor) {
        String sql = "INSERT INTO shop.Processor (threads_count, clock_frequency, max_frequency, cpu_count) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, processor.getThreadsCount());
            stmt.setDouble(2, processor.getClockFrequency());
            stmt.setDouble(3, processor.getMaxFrequency());
            stmt.setInt(4, processor.getCpuCount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Processor getById(int id) {
        String sql = "SELECT * FROM shop.Processor WHERE id = ?";
        Processor processor = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                processor = new Processor(
                        rs.getInt("id"),
                        rs.getInt("threads_count"),
                        rs.getDouble("clock_frequency"),
                        rs.getDouble("max_frequency"),
                        rs.getInt("cpu_count")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processor;
    }

    public List<Processor> getAll() {
        String sql = "SELECT * FROM shop.Processor";
        List<Processor> processors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                processors.add(new Processor(
                        rs.getInt("id"),
                        rs.getInt("threads_count"),
                        rs.getDouble("clock_frequency"),
                        rs.getDouble("max_frequency"),
                        rs.getInt("cpu_count")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processors;
    }

    public void update(Processor processor) {
        String sql = "UPDATE shop.Processor SET threads_count = ?, clock_frequency = ?, max_frequency = ?, cpu_count = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, processor.getThreadsCount());
            stmt.setDouble(2, processor.getClockFrequency());
            stmt.setDouble(3, processor.getMaxFrequency());
            stmt.setInt(4, processor.getCpuCount());
            stmt.setInt(5, processor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Processor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}