import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Products {

    private String name;
    private Integer quantity;
    private Integer destination;

    Database data = new Database();

    public Products(){
        
    }

    public Products(String name, Integer quantity, Integer destination){
        this.name = name;
        this.quantity = quantity;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
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
                data.addProduct(productNameField.getText(), Integer.parseInt(productQuantityField.getText()), Integer.parseInt(productDestinationField.getText()));
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
