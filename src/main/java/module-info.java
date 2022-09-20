module com.example.thesis_demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thesis_demo to javafx.fxml;
    exports com.example.thesis_demo;
}