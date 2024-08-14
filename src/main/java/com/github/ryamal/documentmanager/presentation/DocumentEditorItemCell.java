package com.github.ryamal.documentmanager.presentation;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DocumentEditorItemCell extends ListCell<DocumentAttributeModel> {
    private final HBox hbox = new HBox();
    private final Text key = new Text();
    private final TextField value = new TextField();

    public DocumentEditorItemCell() {
        super();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10);
        value.textProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.contains(",")) {
                value.setText(oldValue);
            }
        });
        hbox.getChildren().addAll(key, value);
    }

    @Override
    protected void updateItem(DocumentAttributeModel item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            key.setText(item.key.getValue());
            item.value.bind(value.textProperty());
            setGraphic(hbox);
        }
    }
}