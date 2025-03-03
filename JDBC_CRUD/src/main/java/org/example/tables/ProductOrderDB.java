package org.example.tables;

import org.example.DatabaseConnection;
import org.example.models.ProductOrder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderDB {


    public void add(ProductOrder productOrder) {
        String sql = "INSERT INTO shop.Product_Order (product_id, order_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productOrder.getProductId());
            stmt.setInt(2, productOrder.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductOrder getById(int id) {
        String sql = "SELECT * FROM shop.Product_Order WHERE id = ?";
        ProductOrder productOrder = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                productOrder = new ProductOrder(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("order_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productOrder;
    }

    public List<ProductOrder> getAll() {
        String sql = "SELECT * FROM shop.Product_Order";
        List<ProductOrder> productOrders = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productOrders.add(new ProductOrder(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("order_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productOrders;
    }

    public void update(ProductOrder productOrder) {
        String sql = "UPDATE shop.Product_Order SET product_id = ?, order_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productOrder.getProductId());
            stmt.setInt(2, productOrder.getOrderId());
            stmt.setInt(3, productOrder.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Product_Order WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}