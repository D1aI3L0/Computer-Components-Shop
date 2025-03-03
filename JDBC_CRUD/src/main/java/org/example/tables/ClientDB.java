package org.example.tables;

import org.example.DatabaseConnection;
import org.example.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDB {

    private static final String INSERT_CLIENT_SQL = "INSERT INTO shop.Client (name, surname, patronymic, phone_number, email_address) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_CLIENT_BY_ID = "SELECT id, name, surname, patronymic, phone_number, email_address FROM shop.Client WHERE id = ?;";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM shop.Client;";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM shop.Client WHERE id = ?;";
    private static final String UPDATE_CLIENT_SQL = "UPDATE shop.Client SET name = ?, surname = ?, patronymic = ?, phone_number = ?, email_address = ? WHERE id = ?;";


    public void add(Client client) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPatronymic());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.setString(5, client.getEmailAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Client getById(int id) {
        Client client = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String phoneNumber = rs.getString("phone_number");
                String emailAddress = rs.getString("email_address");
                client = new Client(id, name, surname, patronymic, phoneNumber, emailAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }


    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String phoneNumber = rs.getString("phone_number");
                String emailAddress = rs.getString("email_address");
                clients.add(new Client(id, name, surname, patronymic, phoneNumber, emailAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public void update(Client client) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPatronymic());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.setString(5, client.getEmailAddress());
            preparedStatement.setInt(6, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
