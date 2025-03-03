package org.example.tables;

import org.example.DatabaseConnection;
import org.example.models.Order;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDB {


    public void add(Order order) {
        String sql = "INSERT INTO shop.Order (total_price, order_date, status, payment_method, client_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, order.getTotalPrice());
            stmt.setObject(2, LocalDate.parse(order.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(3, order.getStatus());
            stmt.setString(4, order.getPaymentMethod());
            stmt.setInt(5, order.getClientId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getById(int id) {
        String sql = "SELECT * FROM shop.Order WHERE id = ?";
        Order order = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getDouble("total_price"),
                        rs.getDate("order_date").toString(),
                        rs.getString("status"),
                        rs.getString("payment_method"),
                        rs.getInt("client_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getAll() {
        String sql = "SELECT * FROM shop.Order";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getDouble("total_price"),
                        rs.getDate("order_date").toString(),
                        rs.getString("status"),
                        rs.getString("payment_method"),
                        rs.getInt("client_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void update(Order order) {
        String sql = "UPDATE shop.Order SET total_price = ?, order_date = ?, status = ?, payment_method = ?, client_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, order.getTotalPrice());
            stmt.setObject(2, LocalDate.parse(order.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(3, order.getStatus());
            stmt.setString(4, order.getPaymentMethod());
            stmt.setInt(5, order.getClientId());
            stmt.setInt(6, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shop.Order WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}