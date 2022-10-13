module com.example.thesis_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.thesis_demo to javafx.fxml;
    exports com.example.thesis_demo;
}