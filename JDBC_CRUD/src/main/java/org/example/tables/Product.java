package org.example.tables;

import org.example.DatabaseConnection;
import org.postgresql.util.PGmoney;

import java.math.BigDecimal;
import java.sql.*;

public class Product {


    public void create(BigDecimal price, String name, String manufacturer, ProductType type, int processorId, int motherboardId, int graphicCardId) {
        String sql = "INSERT INTO shop.Product (price, name, manufacturer, type, processor_id, motherboard_id, graphic_card_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, price);
            pstmt.setString(2, name);
            pstmt.setString(3, manufacturer);
            pstmt.setString(4, type.name());
            switch (type) {
                case Processor:
                    pstmt.setInt(5, processorId);
                    pstmt.setNull(6, java.sql.Types.INTEGER); // motherboard_id = NULL
                    pstmt.setNull(7, java.sql.Types.INTEGER); // graphic_card_id = NULL
                    break;
                case Motherboard:
                    pstmt.setNull(5, java.sql.Types.INTEGER); // processor_id = NULL
                    pstmt.setInt(6, motherboardId);
                    pstmt.setNull(7, java.sql.Types.INTEGER); // graphic_card_id = NULL
                    break;
                case GraphicCard:
                    pstmt.setNull(5, java.sql.Types.INTEGER); // processor_id = NULL
                    pstmt.setNull(6, java.sql.Types.INTEGER); // motherboard_id = NULL
                    pstmt.setInt(7, graphicCardId);
                    break;
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void read(int id) {
        String sql = "SELECT * FROM shop.Product WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Manufacturer: " + rs.getString("manufacturer"));
                // Преобразуем строку обратно в enum
                ProductType type = ProductType.valueOf(rs.getString("type"));
                System.out.println("Type: " + type);
                System.out.println("Processor ID: " + rs.getInt("processor_id"));
                System.out.println("Motherboard ID: " + rs.getInt("motherboard_id"));
                System.out.println("Graphic Card ID: " + rs.getInt("graphic_card_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, BigDecimal price, String name, String manufacturer, ProductType type, int processorId, int motherboardId, int graphicCardId) {
        String sql = "UPDATE shop.Product SET price = ?, name = ?, manufacturer = ?, type = ?, processor_id = ?, motherboard_id = ?, graphic_card_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, price);
            pstmt.setString(2, name);
            pstmt.setString(3, manufacturer);
            pstmt.setString(4, type.name());

            switch (type) {
                case Processor:
                    pstmt.setInt(5, processorId);
                    pstmt.setNull(6, java.sql.Types.INTEGER);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    break;
                case Motherboard:
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setInt(6, motherboardId);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    break;
                case GraphicCard:
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setNull(6, java.sql.Types.INTEGER);
                    pstmt.setInt(7, graphicCardId);
                    break;
            }
            pstmt.setInt(8, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM shop.Product WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}