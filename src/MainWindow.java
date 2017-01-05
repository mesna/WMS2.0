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

    private Database data = new Database();
    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    public MainWindow() {

        Stage window = new Stage();
        BorderPane layout = new BorderPane();
        try {
            productList.addAll(data.displayProducts());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MenuButton btnProducts = new MenuButton("Product");
        MenuItem addProductBtn = new MenuItem("Add Product");
        addProductBtn.setOnAction(e -> {
            try {
                addProductPage();
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

        layout.setTop(toolbar);
        layout.setCenter(table);
        layout.setStyle("-fx-background-color: #C1E3D4");

        Scene scene = new Scene(layout,500,350);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }
    public void addProductPage() throws ClassNotFoundException, SQLException{

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
                data.addProduct(newProduct);
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
}
