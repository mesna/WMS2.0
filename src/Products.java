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

    Database data = new Database();

    public void addProduct() throws ClassNotFoundException, SQLException{

        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        TextField pName = new TextField();
        pName.setPromptText("Product name");
        TextField pQty = new TextField();
        pQty.setPromptText("Quantity");
        TextField pLocation = new TextField();
        pLocation.setPromptText("Location (Example 12345)");
        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            try {
                data.addProduct(pName.getText(), Integer.parseInt(pQty.getText()), Integer.parseInt(pLocation.getText()));
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
        grid.add(pName, 0, 0);
        grid.add(pQty, 0, 1);
        grid.add(pLocation, 0, 2);
        grid.add(addBtn, 0, 3);
        Scene content = new Scene(grid, 200,200);
        window.setScene(content);
        window.show();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addBtn.requestFocus();
            }
        });
    }
}
