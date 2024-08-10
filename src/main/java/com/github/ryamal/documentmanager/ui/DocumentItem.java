package com.github.ryamal.documentmanager.ui;

public class DocumentItem {
    private String title;
    private String date;
    private String number;

    public DocumentItem(String title, String date, String number) {
        this.title = title;
        this.date = date;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
