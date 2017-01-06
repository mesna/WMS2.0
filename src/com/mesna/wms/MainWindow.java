package com.mesna.wms;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainWindow {

    private Database data;
    private ProductsDAO productsDAO;
    private TableView<Product> table;
    private ObservableList<Product> productList;
    private CustomToolBar toolBar;

    public MainWindow() {

        data = new Database();
        productsDAO = new ProductsDAO(data);
        productList = FXCollections.observableArrayList();
        toolBar = new CustomToolBar(this);
        table = createTable();
        Stage window = new Stage();
        BorderPane layout = new BorderPane();
        try {
            productList.addAll(productsDAO.getProducts());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        layout.setLeft(toolBar);
        layout.setCenter(table);
        layout.setStyle("-fx-background-color: #C1E3D4");

        Scene scene = new Scene(layout,550,350);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }
    public TableView createTable(){

        TableView table = new TableView();
        TableColumn products = new TableColumn("Product");
        TableColumn quantity = new TableColumn("Quantity");
        TableColumn destination = new TableColumn("Location");

        products.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        destination.setCellValueFactory(new PropertyValueFactory<Product, Integer>("destination"));

        products.prefWidthProperty().bind(table.widthProperty().multiply(0.66));
        quantity.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        destination.prefWidthProperty().bind(table.widthProperty().multiply(0.17));

        products.setResizable(false);
        quantity.setResizable(false);
        destination.setResizable(false);

        table.getColumns().add(products);
        table.getColumns().add(quantity);
        table.getColumns().add(destination);
        table.setItems(productList);
        return table;
    }

    public void addProductPage() throws ClassNotFoundException, SQLException{
        System.out.println("Sakkdiip");
        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField productNameField = new TextField();
        productNameField.setPromptText("Product name");
        TextField productQuantityField = new TextField();
        productQuantityField.setPromptText("Quantity");
        TextField productDestinationField = new TextField();
        productDestinationField.setPromptText("Location (Example 12345)");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                Product newProduct = new Product(productNameField.getText(),Integer.parseInt(productQuantityField.getText()),Integer.parseInt(productDestinationField.getText()));
                productsDAO.addProduct(newProduct);
                productList.add(newProduct);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Product successfully added!");
                alert.setHeaderText(null);
                alert.showAndWait();
                window.close();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        grid.add(productNameField, 0, 0);
        grid.add(productQuantityField, 0, 1);
        grid.add(productDestinationField, 0, 2);
        grid.add(addButton, 0, 3);
        Scene content = new Scene(grid, 200,200);
        window.setScene(content);
        window.show();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addButton.requestFocus();
            }
        });
    }

    public void deleteProduct() throws ClassNotFoundException, SQLException{

        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(p -> {allProducts.remove(p); productsDAO.deleteProduct(p);});

    }
}
