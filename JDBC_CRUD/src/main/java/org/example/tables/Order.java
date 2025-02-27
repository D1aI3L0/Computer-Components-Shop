package org.example.tables;

import org.example.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Order {


    public void create(BigDecimal totalPrice, LocalDate orderDate, OrderStatus status, String paymentMethod, int clientId) {
        String sql = "INSERT INTO shop.Order (total_price, order_date, status, payment_method, client_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, totalPrice);
            pstmt.setObject(2, orderDate);
            pstmt.setString(3, status.name());
            pstmt.setString(4, paymentMethod);
            pstmt.setInt(5, clientId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void read(int id) {
        String sql = "SELECT * FROM shop.Order WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Total Price: " + rs.getBigDecimal("total_price"));
                System.out.println("Order Date: " + rs.getDate("order_date"));
                OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                System.out.println("Status: " + status);
                System.out.println("Payment Method: " + rs.getString("payment_method"));
                System.out.println("Client ID: " + rs.getInt("client_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, BigDecimal totalPrice, LocalDate orderDate, OrderStatus status, String paymentMethod, int clientId) {
        String sql = "UPDATE shop.Order SET total_price = ?, order_date = ?, status = ?, payment_method = ?, client_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, totalPrice);
            pstmt.setObject(2, orderDate);
            pstmt.setString(3, status.name());
            pstmt.setString(4, paymentMethod);
            pstmt.setInt(5, clientId);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM shop.Order WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}