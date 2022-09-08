module com.example.dungen111 {
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.tibia to javafx.fxml;
    exports com.example.tibia;
    exports com.example.tibia.controller;
    opens com.example.tibia.controller to javafx.fxml;
}