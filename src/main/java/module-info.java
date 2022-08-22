module com.example.dungen111 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tibia to javafx.fxml;
    exports com.example.tibia;
}