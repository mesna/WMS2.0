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

    public ObservableList<Products> displayProducts() throws ClassNotFoundException, SQLException {

        ObservableList<Products> productsList = FXCollections.observableArrayList();
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT name, quantity, destination FROM products");

                while(rs.next()) {
                    productsList.add(new Products(rs.getString("name"), rs.getInt("quantity"), rs.getInt("destination")));
                }
            }
        }
        return productsList;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:wms.db");
    }

    private void createDatabaseIfNecessary() throws SQLException {
        if (isDatabaseCreated()) {
            return;
        }
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                System.out.println("Creating a table for Products");
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

    public void addProduct(String productName, Integer productQuantity, Integer productLocation) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement productAdd = connection.prepareStatement("INSERT INTO products VALUES(?,?,?,?);");
            productAdd.setString(2, productName);
            productAdd.setInt(3, productQuantity);
            productAdd.setInt(4, productLocation);
            productAdd.execute();
        }
    }

}