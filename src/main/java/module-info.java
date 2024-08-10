module com.github.ryamal.documentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;

    opens com.github.ryamal.documentmanager to javafx.fxml;
    opens com.github.ryamal.documentmanager.entity;
    exports com.github.ryamal.documentmanager;
    exports com.github.ryamal.documentmanager.controllers;
    opens com.github.ryamal.documentmanager.controllers to javafx.fxml;
}