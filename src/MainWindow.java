import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainWindow {

    Products product = new Products();

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

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(btnProducts,btnOrders);

        layout.setTop(toolbar);
        layout.setStyle("-fx-background-color: #C1E3D4");

        Scene scene = new Scene(layout,600,400);
        window.setScene(scene);
        window.show();

    }
}
