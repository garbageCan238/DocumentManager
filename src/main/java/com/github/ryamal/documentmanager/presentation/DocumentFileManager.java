package com.github.ryamal.documentmanager.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DocumentFileManager {

    public static void saveDocument(DocumentModel document, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(document.type.get());
            writer.newLine();

            for (DocumentAttributeModel attribute : document.getAttributes()) {
                writer.write(attribute.key.get() + ": " + attribute.value.get());
                writer.newLine();
            }
        }
    }

    public static DocumentModel loadDocument(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));

        StringProperty type = new SimpleStringProperty(lines.getFirst());

        ObservableList<DocumentAttributeModel> attributes = FXCollections.observableArrayList();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                attributes.add(new DocumentAttributeModel(key, value));
            }
        }

        return new DocumentModel(type, attributes);
    }
}