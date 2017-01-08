package com.mesna.wms;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginStage extends Stage{

        private TextField userName;
        private PasswordField passWord;
        private MainWindow mainWindow;

    public LoginStage() {

        userName = new TextField();
        passWord = new PasswordField();
        createLogin();
    }


    public void createLogin(){

        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);

        userName.setPromptText("Username");
        passWord.setPromptText("Password");

        Button login = new Button("Login");
        login.setDefaultButton(true);
        login.setOnAction(e -> checkLogin());
        Button exit = new Button("Exit");
        exit.setOnAction(e -> Platform.exit());

        loginLayout.add(userName,1,0);
        loginLayout.add(passWord,1,1);
        loginLayout.add(login,1,2);
        loginLayout.add(exit,1,3);

        setTitle("WMS 2.0");
        Scene loginWindow = new Scene(loginLayout, 300, 200);
        setScene(loginWindow);
        setResizable(false);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                login.requestFocus();
            }
        });
    }

    private void checkLogin(){

        if(userName.getText().equals("User") && passWord.getText().equals("Pass")){
            mainWindow = new MainWindow();
            close();
        } else{
           wrongLoginAlert();
        }
    }

    public void wrongLoginAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wrong Username or Password!");
        alert.showAndWait();
        passWord.clear();
        userName.clear();
    }


}
