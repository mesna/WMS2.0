import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {

    public MainWindow(){

        Stage window = new Stage();
        BorderPane layout = new BorderPane();
        ToolBar toolbar = new ToolBar();

        layout.setTop(toolbar);
        layout.setStyle("-fx-background-color: #C1E3D4");

        Scene scene = new Scene(layout,600,400);
        window.setScene(scene);
        window.show();

    }
}
