module com.github.ryamal.documentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires com.google.guice;

    opens com.github.ryamal.documentmanager to javafx.fxml;
    opens com.github.ryamal.documentmanager.entity;
    exports com.github.ryamal.documentmanager;
    exports com.github.ryamal.documentmanager.controllers;
    exports com.github.ryamal.documentmanager.util to com.google.guice;
    opens com.github.ryamal.documentmanager.controllers to javafx.fxml, com.google.guice;
    opens com.github.ryamal.documentmanager.presentation to com.google.guice;
}