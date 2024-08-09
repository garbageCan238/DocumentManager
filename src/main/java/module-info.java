module com.github.ryamal.documentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.github.ryamal.documentmanager to javafx.fxml;
    opens com.github.ryamal.documentmanager.entity;
    exports com.github.ryamal.documentmanager;
}