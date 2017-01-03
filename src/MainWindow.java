import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainWindow {

    Products product = new Products();
    Database data = new Database();

    public MainWindow(){

        Stage window = new Stage();
        BorderPane layout = new BorderPane();
        MenuButton btnProducts = new MenuButton("Products");
        MenuItem addProductBtn = new MenuItem("Add Product");
        addProductBtn.setOnAction(e -> {
            try {
                product.addProduct();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem deleteProductBtn = new MenuItem("Delete Product");
        btnProducts.getItems().addAll(addProductBtn,deleteProductBtn);

        MenuButton btnOrders = new MenuButton("Orders");
        MenuItem newOrder = new MenuItem("New Order");
        newOrder.setOnAction(e -> Orders.createOrder());
        MenuItem changeOrder = new MenuItem("Change Order");
        MenuItem deleteOrder = new MenuItem("Delete Order");
        btnOrders.getItems().addAll(newOrder,changeOrder,deleteOrder);
        Button exit = new Button("Exit");
        exit.setOnAction(e -> Platform.exit());

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(btnProducts, btnOrders, new Separator(),exit);

        TableView table = new TableView();
        TableColumn products = new TableColumn("Products");
        TableColumn qty = new TableColumn("Quantity");
        TableColumn location = new TableColumn("Location");
        products.prefWidthProperty().bind(table.widthProperty().multiply(0.66));
        qty.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        location.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        products.setResizable(false);
        qty.setResizable(false);
        location.setResizable(false);
        table.getColumns().addAll(products, qty, location);

        layout.setTop(toolbar);
        layout.setCenter(table);
        layout.setStyle("-fx-background-color: #C1E3D4");

        Scene scene = new Scene(layout,500,350);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }
}
