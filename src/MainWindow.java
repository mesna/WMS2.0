import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {

    public MainWindow(){

        Stage window = new Stage();
        BorderPane layout = new BorderPane();
        MenuButton btnProducts = new MenuButton("Products");
        MenuItem addProductBtn = new MenuItem("Add Product");
        addProductBtn.setOnAction(e -> Products.addProduct());
        MenuItem changeProductBtn = new MenuItem("Change Product");
        changeProductBtn.setOnAction(e -> Products.changeProduct());
        MenuItem deleteProductBtn = new MenuItem("Delete Product");
        deleteProductBtn.setOnAction(e -> Products.deleteProduct());
        btnProducts.getItems().addAll(addProductBtn,changeProductBtn,deleteProductBtn);

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
