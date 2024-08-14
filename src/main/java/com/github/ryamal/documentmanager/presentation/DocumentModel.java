package com.github.ryamal.documentmanager.presentation;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class DocumentModel {
    public final BooleanProperty checked = new SimpleBooleanProperty(false);
    public final StringProperty type;
    public final ObservableList<DocumentAttributeModel> attributes;

    public DocumentModel(StringProperty type, ObservableList<DocumentAttributeModel> attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    public ObservableList<DocumentAttributeModel> getAttributes() {
        return attributes;
    }

    public String getBasicInfo() {
        String basicInfo = "%s от %s номер %s".formatted(type.get(), attributes.get(1).value.get(), attributes.get(0).value.get());
        basicInfo = basicInfo.substring(0, 1).toUpperCase() + basicInfo.substring(1);
        return basicInfo;
    }
}
