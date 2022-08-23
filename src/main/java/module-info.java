module com.example.dungen111 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tibia to javafx.fxml;
    exports com.example.tibia;
    exports com.example.tibia.controller;
    opens com.example.tibia.controller to javafx.fxml;
}