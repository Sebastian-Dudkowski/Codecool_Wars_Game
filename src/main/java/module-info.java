module com.example.dungen111 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dungen111 to javafx.fxml;
    exports com.example.dungen111;
}