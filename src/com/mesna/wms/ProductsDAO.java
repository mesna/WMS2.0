package com.mesna.wms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProductsDAO {

    private Database data;

    public ProductsDAO(Database data) {
        this.data = data;
    }

    public ObservableList<Product> getProducts() throws ClassNotFoundException, SQLException {

        ObservableList<Product> productList = FXCollections.observableArrayList();
        try (Connection connection = data.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT name, quantity, destination FROM products");

                while(rs.next()) {
                    productList.add(new Product(rs.getString("name"), rs.getInt("quantity"), rs.getInt("destination")));
                }
            }
        }
        return productList;
    }

    public void addProduct(Product product) throws ClassNotFoundException, SQLException {
        try (Connection connection = data.getConnection()) {
            try (PreparedStatement productAdd = connection.prepareStatement("INSERT INTO products VALUES(?,?,?,?);")) {
                productAdd.setString(2, product.getName());
                productAdd.setInt(3, product.getQuantity());
                productAdd.setInt(4, product.getDestination());
                productAdd.execute();
            }
        }
    }

    public void deleteProduct(Product product){
        try(Connection connection = data.getConnection()) {
            try(PreparedStatement productDelete = connection.prepareStatement("DELETE FROM products WHERE name = ? AND quantity = ? AND destination = ? ")){
                productDelete.setString(1, product.getName());
                productDelete.setInt(2, product.getQuantity());
                productDelete.setInt(3, product.getDestination());
                productDelete.execute();
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }
    }

}
