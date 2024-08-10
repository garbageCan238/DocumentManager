package com.github.ryamal.documentmanager.ui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DocumentItemCell extends ListCell<DocumentItem> {
    private HBox hbox = new HBox();
    private CheckBox checkBox = new CheckBox();
    private Text title = new Text();
    private Text date = new Text();
    private Text number = new Text();

    public DocumentItemCell() {
        super();
        hbox.setSpacing(10);

        hbox.getChildren().addAll(checkBox, title, date, number);
    }

    @Override
    protected void updateItem(DocumentItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            title.setText(item.getTitle());
            date.setText(item.getDate());
            number.setText(item.getNumber());

            setGraphic(hbox);
        }
    }
}