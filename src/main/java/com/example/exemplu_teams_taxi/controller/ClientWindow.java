package com.example.exemplu_teams_taxi.controller;

import com.example.exemplu_teams_taxi.domain.Client;
import com.example.exemplu_teams_taxi.domain.Comanda;
import com.example.exemplu_teams_taxi.domain.Persoana;
import com.example.exemplu_teams_taxi.domain.Sofer;
import com.example.exemplu_teams_taxi.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

public class ClientWindow {
    private Service service;
    @FXML
    private TextField locatie_text;
    private Long id_sofer;
    @FXML

    private TextField info;
    public Long id ;
    public void set_client_window(Service service, Long id) {
        this.service=service;
        this.id=id;
    }

    public void accepta(){
        Comanda c=service.get_comanda_dupa_id_client(id);
        service.sterge_comanda(c.getId());
        Comanda c_noua = new Comanda(c.getId(), id_sofer, c.getId_client(), LocalDateTime.now());
        service.adauga_comanda(c_noua);

        service.notifica_soferi();

    }

    public void cancel(){

    }

    public void showInfo(String nume,String durata,Long id_sofer){
        this.id_sofer=id_sofer;
        String mesaj = "Soferul: "+ nume+" vine in "+durata+" minute!";
        info.setText(mesaj);
    }

    public void cauta(){
        String locatie = locatie_text.getText();
        Client client = service.get_client(this.id);

        service.sterge_client(client.getId());
        Client c =new Client(id,locatie);
        service.adauga_client(c);



        // Generează un număr aleatoriu între 1 și 10000
        Random random = new Random();
        long numarAleatoriu = random.nextLong();
        LocalDateTime data = LocalDateTime.now();
        Comanda com = new Comanda(numarAleatoriu,0L,id,data);
        com.setLocatie(locatie);
        service.adauga_comanda(com);
        service.notifica_soferi();






    }
}
