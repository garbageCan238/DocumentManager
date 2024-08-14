package com.github.ryamal.documentmanager.presentation;

import com.github.ryamal.documentmanager.domain.DocumentRepository;
import com.github.ryamal.documentmanager.entity.AbstractDocumentEntity;
import com.github.ryamal.documentmanager.entity.DocumentMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DocumentsViewModel implements DocumentsViewModelInterface {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final ObservableList<DocumentModel> documentsList = FXCollections.observableArrayList();
    private DocumentModel selectedDocument = null;
    private DocumentModel viewedDocument = null;

    @Inject
    public DocumentsViewModel(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public void loadFromFile(File file) throws IOException, RuntimeException {
        try {
            DocumentModel document = DocumentFileManager.loadDocument(file);
            AbstractDocumentEntity entity = documentMapper.mapPresentationToDomain(document);
            documentRepository.save(entity);
            DocumentModel savedDocument = documentMapper.mapDomainToPresentation(entity);
            documentsList.add(savedDocument);
        } catch (IOException e) {
            throw new IOException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveToFile(File file) throws IOException {
        DocumentFileManager.saveDocument(selectedDocument, file);
    }

    @Override
    public void saveCreated() throws RuntimeException {
        AbstractDocumentEntity entity = documentMapper.mapPresentationToDomain(viewedDocument);
        documentRepository.save(entity);
        DocumentModel savedDocument = documentMapper.mapDomainToPresentation(entity);
        documentsList.add(savedDocument);
        viewedDocument = null;
        System.out.println(documentRepository.getAllSorted());
    }

    @Override
    public void deleteChecked() {
        List<AbstractDocumentEntity> entities = new ArrayList<>();
        for (DocumentModel item : documentsList.filtered(item -> item.checked.get())) {
            entities.add(documentMapper.mapPresentationToDomain(item));
        }
        documentRepository.deleteMultiple(entities);
        documentsList.removeAll(
                documentsList.filtered(item -> item.checked.get())
        );
        System.out.println(documentRepository.getAllSorted());
    }

    @Override
    public void createNewInvoice() {
        viewedDocument = DocumentFactory.createEmptyInvoice();
    }

    @Override
    public ObservableList<DocumentModel> getDocumentItems() {
        return documentsList;
    }

    @Override
    public void createNewPaymentSlip() {
        viewedDocument = DocumentFactory.createEmptyPaymentSlip();
    }

    @Override
    public void createNewPaymentRequest() {
        viewedDocument = DocumentFactory.createEmptyPaymentRequest();
    }

    @Override
    public void viewSelectedDocument() {
        if (selectedDocument != null) {
            viewedDocument = selectedDocument;
        }
    }

    @Override
    public void setSelectedDocument(DocumentModel selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    @Override
    public DocumentModel getSelectedDocument() {
        return selectedDocument;
    }

    @Override
    public DocumentModel getViewedDocument() {
        return viewedDocument;
    }

    @Override
    public void cancelEditing() {
        viewedDocument = null;
        System.out.println(documentRepository.getAllSorted());
    }

}
