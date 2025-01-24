module com.example.gui_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens application to javafx.fxml;
    exports application;
    exports application.gui;
    opens application.gui to javafx.fxml;
}