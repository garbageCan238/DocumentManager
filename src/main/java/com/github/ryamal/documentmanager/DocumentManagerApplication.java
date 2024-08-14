package com.github.ryamal.documentmanager;

import com.github.ryamal.documentmanager.util.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class DocumentManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader fxmlLoader = new FXMLLoader(DocumentManagerApplication.class.getResource("main-view.fxml"));
        fxmlLoader.setControllerFactory(injector::getInstance);

        Scene scene = new Scene(fxmlLoader.load());
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