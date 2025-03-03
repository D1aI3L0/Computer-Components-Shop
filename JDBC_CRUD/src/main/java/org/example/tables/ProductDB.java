package org.example.tables;

import org.example.DatabaseConnection;
import org.example.models.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO shop.Product (name, manufacturer, type, price, processor_id, motherboard_id, graphic_card_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT id, name, manufacturer, type, price, processor_id, motherboard_id, graphic_card_id FROM shop.Product WHERE id = ?;";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM shop.Product;";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM shop.Product WHERE id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE shop.Product SET name = ?, manufacturer = ?, type = ?, price = ?, processor_id = ?, motherboard_id = ?, graphic_card_id = ? WHERE id = ?;";

    public void add(Product product) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getManufacturer());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setDouble(4, product.getPrice());
            if(product.getProcessorId() == 0)
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            else
                preparedStatement.setInt(5, product.getProcessorId());
            if(product.getMotherboardId() == 0)
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            else
                preparedStatement.setInt(6, product.getMotherboardId());
            if(product.getGraphicCardId() == 0)
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
            else
                preparedStatement.setInt(7, product.getGraphicCardId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getById(int id) {
        Product product = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                int processorId = rs.getInt("processor_id");
                int motherboardId = rs.getInt("motherboard_id");
                int graphicCardId = rs.getInt("graphic_card_id");
                product = new Product(id, name, manufacturer, type, price, processorId, motherboardId, graphicCardId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                int processorId = rs.getInt("processor_id");
                int motherboardId = rs.getInt("motherboard_id");
                int graphicCardId = rs.getInt("graphic_card_id");
                products.add(new Product(id, name, manufacturer, type, price, processorId, motherboardId, graphicCardId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void update(Product product) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getManufacturer());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setDouble(4, product.getPrice());
            if(product.getProcessorId() == 0)
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            else
                preparedStatement.setInt(5, product.getProcessorId());
            if(product.getMotherboardId() == 0)
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            else
                preparedStatement.setInt(6, product.getMotherboardId());
            if(product.getGraphicCardId() == 0)
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
            else
                preparedStatement.setInt(7, product.getGraphicCardId());
            preparedStatement.setInt(8, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}