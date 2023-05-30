module ma.barkouch.laptopapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires json.simple;
    requires gson;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens ma.barkouch.laptopapp to javafx.fxml;
    opens ma.barkouch.laptopapp.entities to javafx.base;
    opens ma.barkouch.laptopapp.views to javafx.fxml;

    exports ma.barkouch.laptopapp;
    exports ma.barkouch.laptopapp.views;
    exports ma.barkouch.laptopapp.entities;
    exports ma.barkouch.laptopapp.views.crud;
    opens ma.barkouch.laptopapp.views.crud to javafx.fxml;
}