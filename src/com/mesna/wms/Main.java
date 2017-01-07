package com.mesna.wms;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage) throws Exception{
        LoginStage login = new LoginStage();
        login.show();
    }
}
