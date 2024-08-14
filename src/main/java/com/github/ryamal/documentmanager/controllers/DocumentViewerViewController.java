package com.github.ryamal.documentmanager.controllers;

import com.github.ryamal.documentmanager.presentation.DocumentAttributeModel;
import com.github.ryamal.documentmanager.presentation.DocumentViewerItemCell;
import com.github.ryamal.documentmanager.presentation.DocumentsViewModelInterface;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentViewerViewController implements Initializable {
    private final DocumentsViewModelInterface viewModel;
    private Stage stage;

    @Inject
    public DocumentViewerViewController(DocumentsViewModelInterface viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private ListView<DocumentAttributeModel> documentAttributesListView;
    @FXML
    private Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            stage = (Stage) okButton.getScene().getWindow();
            stage.setOnCloseRequest(_ ->
                    viewModel.cancelEditing());
        });

        documentAttributesListView.setCellFactory(_ -> new DocumentViewerItemCell());

        documentAttributesListView.getItems().addAll(
                viewModel.getViewedDocument().getAttributes()
        );

        okButton.setOnAction(_ -> stage.close());
    }
}
