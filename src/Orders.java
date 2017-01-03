import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Orders {

    public static void createOrder(){
        Stage order = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        TextField clientName = new TextField();
        TextField clientCompany = new TextField();
        TextField clientOrder = new TextField();
        grid.add(clientOrder, 0, 1);
        grid.add(clientName, 0, 2);
        grid.add(clientCompany, 0, 3);
        clientOrder.setPromptText("Order number");
        clientName.setPromptText("Client name");
        clientCompany.setPromptText("Company");
        Scene orderScene = new Scene(grid, 300,200);
        order.setScene(orderScene);
        order.show();
    }
}
