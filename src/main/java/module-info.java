module com.example.exemplu_teams_taxi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.exemplu_teams_taxi to javafx.fxml;
    exports com.example.exemplu_teams_taxi;


    exports com.example.exemplu_teams_taxi.controller;
    opens com.example.exemplu_teams_taxi.controller;
    exports com.example.exemplu_teams_taxi.domain;
    opens com.example.exemplu_teams_taxi.domain;
    exports com.example.exemplu_teams_taxi.repository;
    opens com.example.exemplu_teams_taxi.repository;
    exports com.example.exemplu_teams_taxi.service;
    opens com.example.exemplu_teams_taxi.service;
}