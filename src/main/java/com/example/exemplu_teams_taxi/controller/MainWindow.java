package com.example.exemplu_teams_taxi.controller;

import com.example.exemplu_teams_taxi.Main;
import com.example.exemplu_teams_taxi.domain.Persoana;
import com.example.exemplu_teams_taxi.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MainWindow {
    private Service service;
    @FXML
    private TextField username_text;
    public void initMainWindow(Service service) {
        this.service=service;
    }


    public void deschide_fereastra(){
        String username = username_text.getText();
        Optional pers = service.get_persoana_dupa_username(username);
        if(pers.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            // Setează titlul ferestrei
            alert.setTitle("Mesaj informativ");

            // Setează textul mesajului
            alert.setHeaderText(null); // Pentru a elimina antetul
            alert.setContentText("Nu exista acest user!");

            // Arată fereastra modală
            alert.showAndWait();
            return;
        }
        Persoana persoana = (Persoana) pers.get();


        if(Objects.equals(service.sofer_sau_client(persoana.getId()), "client")){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/client-window.fxml"));
                Stage stage = new Stage();

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);



                Stage dialogStage = new Stage();
                dialogStage.setTitle("CLIENT: "+ persoana.getNume());
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(primaryStage);
                dialogStage.setScene(scene);


                //UserEditController userEntityController = fxmlLoader.getController();
                //userEntityController.setUserEditController(service, dialogStage, ut);
                ClientWindow client_window = fxmlLoader.getController();
                client_window.set_client_window(service,persoana.getId());

                //friendRequestView.

                try {
                    dialogStage.show();
                    service.adauga_fereastra_clienti(client_window);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //dialogStage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }



        if(Objects.equals(service.sofer_sau_client(persoana.getId()), "sofer")){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sofer-window.fxml"));
                Stage stage = new Stage();

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);



                Stage dialogStage = new Stage();
                dialogStage.setTitle("SOFER: "+ persoana.getNume());
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(primaryStage);
                dialogStage.setScene(scene);


                //UserEditController userEntityController = fxmlLoader.getController();
                //userEntityController.setUserEditController(service, dialogStage, ut);
                SoferWindow sofer_window = fxmlLoader.getController();
                sofer_window.set_sofer_window(service,persoana.getId());

                //friendRequestView.

                try {
                    dialogStage.show();
                    service.adauga_fereastra_soferi(sofer_window);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //dialogStage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
