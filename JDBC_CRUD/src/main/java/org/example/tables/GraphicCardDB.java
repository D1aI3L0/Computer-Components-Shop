package org.example.tables;

import org.example.models.GraphicCard;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.DatabaseConnection.getConnection;

public class GraphicCardDB {


    public void add(GraphicCard graphicCard) {
        String sql = "INSERT INTO shop.Graphic_Card (gpu_count, gpu_frequency, memory_count, memory_frequency) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, graphicCard.getGpuCount());
            stmt.setDouble(2, graphicCard.getGpuFrequency());
            stmt.setInt(3, graphicCard.getMemoryCount());
            stmt.setDouble(4, graphicCard.getMemoryFrequency());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GraphicCard getById(int id) {
        String sql = "SELECT * FROM shop.Graphic_Card WHERE id = ?";
        GraphicCard graphicCard = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                graphicCard = new GraphicCard(
                        rs.getInt("id"),
                        rs.getInt("gpu_count"),
                        rs.getDouble("gpu_frequency"),
                        rs.getInt("memory_count"),
                        rs.getDouble("memory_frequency")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return graphicCard;
    }

    public List<GraphicCard> getAll() {
        String sql = "SELECT * FROM shop.Graphic_Card";
        List<GraphicCard> graphicCards = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                graphicCards.add(new GraphicCard(
                        rs.getInt("id"),
                        rs.getInt("gpu_count"),
                        rs.getDouble("gpu_frequency"),
                        rs.getInt("memory_count"),
                        rs.getDouble("memory_frequency")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return graphicCards;
    }

    public void update(GraphicCard graphicCard) {
        String sql = "UPDATE shop.Graphic_Card SET gpu_count = ?, gpu_frequency = ?, memory_count = ?, memory_frequency = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, graphicCard.getGpuCount());
            stmt.setDouble(2, graphicCard.getGpuFrequency());
            stmt.setInt(3, graphicCard.getMemoryCount());
            stmt.setDouble(4, graphicCard.getMemoryFrequency());
            stmt.setInt(5, graphicCard.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Graphic_Card WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}