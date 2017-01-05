import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainWindow {

    private Products product = new Products();
    private Database data = new Database();
    private TableView<Products> table;

    public MainWindow(){

        Stage window = new Stage();
        BorderPane layout = new BorderPane();
        MenuButton btnProducts = new MenuButton("Products");
        MenuItem addProductBtn = new MenuItem("Add Product");
        addProductBtn.setOnAction(e -> {
            try {
                product.addProductPage();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem deleteProductBtn = new MenuItem("Delete Product");
        btnProducts.getItems().addAll(addProductBtn,deleteProductBtn);

        Button exit = new Button("Exit");
        exit.setOnAction(e -> Platform.exit());

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(btnProducts, new Separator(),exit);

        TableColumn products = new TableColumn("Products");
        TableColumn quantity = new TableColumn("Quantity");
        TableColumn destination = new TableColumn("Location");
        products.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Products, Integer>("quantity"));
        destination.setCellValueFactory(new PropertyValueFactory<Products, Integer>("destination"));
        products.prefWidthProperty().bind(table.widthProperty().multiply(0.66));
        quantity.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        destination.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        products.setResizable(false);
        quantity.setResizable(false);
        destination.setResizable(false);
        table.getColumns().add(products);
        table.getColumns().add(quantity);
        table.getColumns().add(destination);
        try {
            table.setItems(data.displayProducts());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        layout.setTop(toolbar);
        layout.setCenter(table);
        layout.setStyle("-fx-background-color: #C1E3D4");

        Scene scene = new Scene(layout,500,350);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }
}
