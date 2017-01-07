package com.mesna.wms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {

    public Database() {
        try {
            createDatabaseIfNecessary();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:wms.db");
    }

    private void createDatabaseIfNecessary() throws SQLException {
        if (isDatabaseCreated()) {
            return;
        }
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                System.out.println("Creating a table for Product");
                statement.execute("CREATE TABLE products (id integer,name varchar(30),quantity integer, destination integer, primary key(id))");
            }
        }
    }

    private boolean isDatabaseCreated() throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement selectStatement = connection.createStatement()) {
                ResultSet result = selectStatement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='products'");
                if (result.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}