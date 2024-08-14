package com.github.ryamal.documentmanager.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DocumentAttributeModel {
    public StringProperty key = new SimpleStringProperty();
    public StringProperty value = new SimpleStringProperty();

    public DocumentAttributeModel(String key, String value) {
        this.key.set(key);
        this.value.set(value);
    }
}
