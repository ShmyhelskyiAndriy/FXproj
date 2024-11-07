package com.example.fxproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Адресна книга");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(550);
        primaryStage.setScene(new Scene(root, 600, 550));

        primaryStage.show();


    }



}