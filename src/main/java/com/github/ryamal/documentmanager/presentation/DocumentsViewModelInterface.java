package com.github.ryamal.documentmanager.presentation;

import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;

public interface DocumentsViewModelInterface {

    void loadFromFile(File file) throws IOException, RuntimeException;

    void saveToFile(File file) throws IOException;

    void saveCreated() throws RuntimeException;

    void deleteChecked();

    void createNewInvoice();

    ObservableList<DocumentModel> getDocumentItems();

    void createNewPaymentSlip();

    void createNewPaymentRequest();

    void viewSelectedDocument();

    void setSelectedDocument(DocumentModel selectedDocument);

    DocumentModel getSelectedDocument();

    DocumentModel getViewedDocument();

    void cancelEditing();
}