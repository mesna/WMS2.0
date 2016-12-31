import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



/**
 * Created by Marko on 30.12.2016.
 */
public class Login{

    public Login() {

        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);

        TextField userName = new TextField();
        userName.setPromptText("Username");
        PasswordField passWord = new PasswordField();
        passWord.setPromptText("Password");

        Button login = new Button("Login");
        Button exit = new Button("Exit");

        loginLayout.add(userName,1,0);
        loginLayout.add(passWord,1,1);
        loginLayout.add(login,1,2);
        loginLayout.add(exit,1,3);

        Stage loginScreen = new Stage();
        loginScreen.setTitle("WMS 2.0");
        Scene loginWindow = new Scene(loginLayout, 300, 200);
        loginScreen.setScene(loginWindow);
        loginScreen.show();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                login.requestFocus();
            }
        });
    }

}
