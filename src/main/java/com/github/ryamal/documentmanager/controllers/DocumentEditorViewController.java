package com.github.ryamal.documentmanager.controllers;

import com.github.ryamal.documentmanager.presentation.*;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentEditorViewController implements Initializable {
    private final DocumentsViewModelInterface viewModel;
    private Stage stage;

    @Inject
    public DocumentEditorViewController(DocumentsViewModelInterface viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private ListView<DocumentAttributeModel> documentAttributesListView;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentAttributesListView.setCellFactory(_ -> new DocumentEditorItemCell());
        documentAttributesListView.getItems().addAll(
                viewModel.getViewedDocument().getAttributes()
        );

        okButton.setOnAction(_ -> {
            try {
                viewModel.saveCreated();
            } catch (RuntimeException e) {
                new AlertBuilder()
                        .setType(Alert.AlertType.ERROR)
                        .setTitle("Ошибка")
                        .setHeaderText("Документ не создан")
                        .setText("Введите корректные данные.")
                        .show();
            }
            stage.close();
        });
        cancelButton.setOnAction(_ -> {
            viewModel.cancelEditing();
            stage.close();
        });

        Platform.runLater(() -> {
            stage = (Stage) cancelButton.getScene().getWindow();
            stage.setOnCloseRequest(_ ->
                    viewModel.cancelEditing());
        });
    }
}
