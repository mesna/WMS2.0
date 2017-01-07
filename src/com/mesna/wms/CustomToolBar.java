package com.mesna.wms;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.control.*;

import java.sql.SQLException;

public class CustomToolBar extends ToolBar{

    private final MainWindow mainWindow;

    public CustomToolBar(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        initialize();
    }

    public void initialize() {

        MenuButton btnProducts = new MenuButton("Product");
        btnProducts.setMaxWidth(Double.MAX_VALUE);

        MenuItem addProductBtn = new MenuItem("Add Product");
        addProductBtn.setOnAction(e -> {
            try {
                mainWindow.addProductPage();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        MenuItem deleteProductBtn = new MenuItem("Delete Product");
        deleteProductBtn.setOnAction(e -> {
            try {
                mainWindow.deleteProduct();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        btnProducts.getItems().addAll(addProductBtn, deleteProductBtn);

        Button exit = new Button("Exit");
        exit.setMaxWidth(Double.MAX_VALUE);
        exit.setOnAction(e -> Platform.exit());

        Button orders = new Button("Orders");
        orders.setMaxWidth(Double.MAX_VALUE);
        orders.setOnAction(e -> mainWindow.underConstruction());

        Button clients = new Button("Clients");
        clients.setMaxWidth(Double.MAX_VALUE);
        clients.setOnAction(e -> mainWindow.underConstruction());

        Button administration = new Button("Administration");
        administration.setMaxWidth(Double.MAX_VALUE);
        administration.setOnAction(e -> mainWindow.underConstruction());

        setOrientation(Orientation.VERTICAL);
        getItems().addAll(btnProducts, new Separator(), orders, new Separator(), clients, new Separator(), administration, new Separator(), exit);
    }
}
