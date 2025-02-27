package org.example.tables;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {

    public void create(int clientId, int productId, String reviewText) {
        String sql = "INSERT INTO shop.Review (client_id, product_id, Review) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.setInt(2, productId);
            pstmt.setString(3, reviewText);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void read(int id) {
        String sql = "SELECT * FROM shop.Review WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Client ID: " + rs.getInt("client_id"));
                System.out.println("Product ID: " + rs.getInt("product_id"));
                System.out.println("Review: " + rs.getString("Review"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, int clientId, int productId, String reviewText) {
        String sql = "UPDATE shop.Review SET client_id = ?, product_id = ?, Review = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.setInt(2, productId);
            pstmt.setString(3, reviewText);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void detele(int id) {
        String sql = "DELETE FROM shop.Review WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}