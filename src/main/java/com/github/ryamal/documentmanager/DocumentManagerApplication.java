package com.github.ryamal.documentmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class DocumentManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DocumentManagerApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Менеджер документов");
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}