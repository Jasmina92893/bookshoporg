package com.example.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("library.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create database if not exists
        DatabaseUtil.createDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }
}