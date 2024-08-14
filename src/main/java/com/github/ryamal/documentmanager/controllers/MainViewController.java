package com.github.ryamal.documentmanager.controllers;

import com.github.ryamal.documentmanager.DocumentManagerApplication;
import com.github.ryamal.documentmanager.presentation.*;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private final DocumentsViewModelInterface viewModel;
    private Stage stage;
    private final Injector injector;

    @Inject
    public MainViewController(DocumentsViewModelInterface viewModel, Injector injector) {
        this.viewModel = viewModel;
        this.injector = injector;
    }

    @FXML
    private ListView<DocumentModel> documentsListView;
    @FXML
    private Button invoiceButton;
    @FXML
    private Button paymentRequestButton;
    @FXML
    private Button paymentSlipButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button viewButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentsListView.setCellFactory(_ -> new DocumentItemCell());

        documentsListView.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) ->
                viewModel.setSelectedDocument(newValue)
        );

        documentsListView.setItems(viewModel.getDocumentItems());

        invoiceButton.setOnAction(_ -> {
            viewModel.createNewInvoice();
            try {
                openAnotherView("document-editor-view.fxml", viewModel.getViewedDocument().type.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        paymentRequestButton.setOnAction(_ -> {
            viewModel.createNewPaymentRequest();
            try {
                openAnotherView("document-editor-view.fxml", viewModel.getViewedDocument().type.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        paymentSlipButton.setOnAction(_ -> {
            viewModel.createNewPaymentSlip();
            try {
                openAnotherView("document-editor-view.fxml", viewModel.getViewedDocument().type.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        saveButton.setOnAction(_ -> {
            if (viewModel.getSelectedDocument() == null) {
                new AlertBuilder()
                        .setType(Alert.AlertType.ERROR)
                        .setTitle("Ошибка")
                        .setHeaderText("Файл не сохранен")
                        .setText("Вы не выбрали документ для сохранения.")
                        .show();
                return;
            }
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {
                    viewModel.saveToFile(file);
                } catch (Exception e) {
                    new AlertBuilder()
                            .setType(Alert.AlertType.ERROR)
                            .setTitle("Ошибка")
                            .setHeaderText("Файл не сохранен")
                            .setText("Ошибка сохранения.")
                            .show();
                }
            }
        });
        loadButton.setOnAction(_ -> {
            try {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    viewModel.loadFromFile(file);
                }
            } catch (IOException e) {
                new AlertBuilder()
                        .setType(Alert.AlertType.ERROR)
                        .setTitle("Ошибка")
                        .setHeaderText("Файл не загружен")
                        .setText("Файл не содержит описания документа.")
                        .show();
            } catch (RuntimeException e) {
                new AlertBuilder()
                        .setType(Alert.AlertType.ERROR)
                        .setTitle("Ошибка")
                        .setHeaderText("Файл не загружен")
                        .setText("Документ с таким номером уже существует.")
                        .show();
            }
        });

        viewButton.setOnAction(_ -> {
            if (viewModel.getSelectedDocument() == null) {
                new AlertBuilder()
                        .setType(Alert.AlertType.ERROR)
                        .setTitle("Ошибка")
                        .setHeaderText("Не удается просмотреть файл")
                        .setText("Вы не выбрали документ для просмотра.")
                        .show();
            } else {
                viewModel.viewSelectedDocument();
                try {
                    openAnotherView(
                            "document-viewer-view.fxml",
                            viewModel.getSelectedDocument().type.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        deleteButton.setOnAction(_ -> viewModel.deleteChecked());
        exitButton.setOnAction(_ -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        Platform.runLater(() -> stage = (Stage) exitButton.getScene().getWindow());
    }

    private void openAnotherView(String viewFxmlResource, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DocumentManagerApplication.class.getResource(viewFxmlResource));
        fxmlLoader.setControllerFactory(injector::getInstance);

        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.stage);
        stage.setTitle(title);
        stage.setScene(new Scene(root));

        stage.show();
    }
}