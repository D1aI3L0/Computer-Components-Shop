package org.example.tables;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {


    public void create(String phoneNumber, String name, String surname, String patronymic, String email) {
        String sql = "INSERT INTO shop.Client (phone_number, name, surname, patronymic, email_address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, patronymic);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void read(int id) {
        String sql = "SELECT * FROM shop.Client WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Phone Number: " + rs.getString("phone_number"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Surname: " + rs.getString("surname"));
                System.out.println("Patronymic: " + rs.getString("patronymic"));
                System.out.println("Email: " + rs.getString("email_address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, String phoneNumber, String name, String surname, String patronymic, String email) {
        String sql = "UPDATE shop.Client SET phone_number = ?, name = ?, surname = ?, patronymic = ?, email_address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, patronymic);
            pstmt.setString(5, email);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM shop.Client WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
