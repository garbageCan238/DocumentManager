package com.github.ryamal.documentmanager.controllers;

import com.github.ryamal.documentmanager.DocumentManagerApplication;
import com.github.ryamal.documentmanager.ui.DocumentItem;
import com.github.ryamal.documentmanager.ui.DocumentItemCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private ListView<DocumentItem> documentsListView;
    @FXML
    private Button invoiceButton;
    @FXML
    private Button paymentRequestButton;
    @FXML
    private Button paymentSlipButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentsListView.setCellFactory(_ -> new DocumentItemCell());

        invoiceButton.setOnAction(_ -> openDocumentEditingWindow());
        paymentRequestButton.setOnAction(_ -> openDocumentEditingWindow());
        paymentSlipButton.setOnAction(_ -> openDocumentEditingWindow());
    }

    private void openDocumentEditingWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DocumentManagerApplication.class.getResource("document-editing-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Document Editing");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}