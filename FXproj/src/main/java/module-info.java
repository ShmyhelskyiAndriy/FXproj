module com.example.fxproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxproj to javafx.fxml;
    exports com.example.fxproj;
}