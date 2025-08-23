module com.example.demo63457 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo63457 to javafx.fxml;
    exports com.example.demo63457;
}