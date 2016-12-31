import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Orders {

    public static void createOrder(){
        Stage order = new Stage();
        VBox vbox = new VBox(10);
        TextField clientName = new TextField();
        vbox.getChildren().addAll(clientName);
        clientName.setPromptText("Client name");
        Scene orderScene = new Scene(vbox, 300,200);
        order.setScene(orderScene);
        order.show();
    }
}
