package com.github.ryamal.documentmanager.presentation;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DocumentViewerItemCell extends ListCell<DocumentAttributeModel> {
    private final HBox hbox = new HBox();
    private final Text attributeString = new Text();

    public DocumentViewerItemCell() {
        super();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10);

        hbox.getChildren().addAll(attributeString);
    }

    @Override
    protected void updateItem(DocumentAttributeModel item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            attributeString.setText(item.key.getValue() + ": " + item.value.get());
            setGraphic(hbox);
        }
    }
}
