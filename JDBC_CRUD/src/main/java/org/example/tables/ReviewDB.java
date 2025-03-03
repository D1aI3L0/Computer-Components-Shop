package org.example.tables;

import org.example.models.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.DatabaseConnection.getConnection;

public class ReviewDB {

    public void add(Review review) {
        String sql = "INSERT INTO shop.Review (client_id, product_id, review) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getClientId());
            stmt.setInt(2, review.getProductId());
            stmt.setString(3, review.getReview());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Review getById(int id) {
        String sql = "SELECT * FROM shop.Review WHERE id = ?";
        Review review = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                review = new Review(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("product_id"),
                        rs.getString("review")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    public List<Review> getAll() {
        String sql = "SELECT * FROM shop.Review";
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("product_id"),
                        rs.getString("review")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public void update(Review review) {
        String sql = "UPDATE shop.Review SET client_id = ?, product_id = ?, review = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getClientId());
            stmt.setInt(2, review.getProductId());
            stmt.setString(3, review.getReview());
            stmt.setInt(4, review.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM shop.Review WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}