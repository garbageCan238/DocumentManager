package com.github.ryamal.documentmanager.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

public class DocumentFactory {
    public static DocumentModel createEmptyInvoice() {
        return new DocumentModel(new SimpleStringProperty("Накладная"), FXCollections.observableArrayList(
                new DocumentAttributeModel("Номер", ""),
                new DocumentAttributeModel("Дата", ""),
                new DocumentAttributeModel("Пользователь", ""),
                new DocumentAttributeModel("Сумма", ""),
                new DocumentAttributeModel("Валюта", ""),
                new DocumentAttributeModel("Курс валюты", ""),
                new DocumentAttributeModel("Товар", ""),
                new DocumentAttributeModel("Количество", "")
        ));
    }

    public static DocumentModel createEmptyPaymentRequest() {
        return new DocumentModel(new SimpleStringProperty("Платежка"), FXCollections.observableArrayList(
                new DocumentAttributeModel("Номер", ""),
                new DocumentAttributeModel("Дата", ""),
                new DocumentAttributeModel("Пользователь", ""),
                new DocumentAttributeModel("Сумма", ""),
                new DocumentAttributeModel("Сотрудник", "")
        ));
    }

    public static DocumentModel createEmptyPaymentSlip() {
        return new DocumentModel(new SimpleStringProperty("Заявка на оплату"), FXCollections.observableArrayList(
                new DocumentAttributeModel("Номер", ""),
                new DocumentAttributeModel("Дата", ""),
                new DocumentAttributeModel("Пользователь", ""),
                new DocumentAttributeModel("Контрагент", ""),
                new DocumentAttributeModel("Сумма", ""),
                new DocumentAttributeModel("Валюта", ""),
                new DocumentAttributeModel("Курс валюты", ""),
                new DocumentAttributeModel("Комиссия", "")
        ));
    }
}
