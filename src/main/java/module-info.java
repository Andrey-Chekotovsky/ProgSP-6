module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.menu to javafx.fxml;
    exports com.example.menu;
    exports com.example.menu.Controllers;
    exports  com.example.menu.Models;
    opens com.example.menu.Controllers to javafx.fxml;
}