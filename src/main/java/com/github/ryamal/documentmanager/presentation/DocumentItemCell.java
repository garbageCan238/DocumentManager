package com.github.ryamal.documentmanager.presentation;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DocumentItemCell extends ListCell<DocumentModel> {
    private final HBox hbox = new HBox();
    private final CheckBox checkBox = new CheckBox();
    private final Text basicInfo = new Text();

    public DocumentItemCell() {
        super();
        hbox.setSpacing(10);

        hbox.getChildren().addAll(checkBox, basicInfo);
    }

    @Override
    protected void updateItem(DocumentModel item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            checkBox.selectedProperty().setValue(item.checked.get());
            basicInfo.setText(item.getBasicInfo());
            item.checked.bind(checkBox.selectedProperty());
            setGraphic(hbox);
        }
    }
}