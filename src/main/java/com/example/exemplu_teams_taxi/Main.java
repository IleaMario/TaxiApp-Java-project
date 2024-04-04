package com.example.exemplu_teams_taxi;


import com.example.exemplu_teams_taxi.controller.MainWindow;
import com.example.exemplu_teams_taxi.repository.RepoClienti;
import com.example.exemplu_teams_taxi.repository.RepoComenzi;
import com.example.exemplu_teams_taxi.repository.RepoPersoane;
import com.example.exemplu_teams_taxi.repository.RepoSoferi;
import com.example.exemplu_teams_taxi.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {



    @Override

    public void start(Stage stage) throws IOException {

        String url = "***";
        String username = "***";
        String password = "***";
        RepoPersoane repo_persoane = new RepoPersoane(url, username, password);
        RepoClienti repo_clienti = new RepoClienti(url, username, password);
        RepoSoferi repo_soferi = new RepoSoferi(url,username,password);
        RepoComenzi repo_comenzi =  new RepoComenzi(url,username,password);
        Service service = new Service(repo_persoane,repo_soferi,repo_clienti,repo_comenzi);

        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/user-table.fxml"));

        //Scene scene = new Scene(fxmlLoader.load(), 520, 340);
        //UserTableController ctr = fxmlLoader.getController();

        try {



            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/main-window.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 520, 340);
            stage.setTitle("Main application");
            stage.setScene(scene);
            MainWindow ctr = fxmlLoader.getController();
            ctr.initMainWindow(service);


            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //ctr.initUserTable(serv);
        //stage.setTitle("Main Application");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) {



        launch();
    }
}
