package com.example.exemplu_teams_taxi.controller;

import com.example.exemplu_teams_taxi.domain.Client;
import com.example.exemplu_teams_taxi.domain.Comanda;
import com.example.exemplu_teams_taxi.domain.Persoana;
import com.example.exemplu_teams_taxi.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.StreamSupport;

public class SoferWindow {
    @FXML
    ListView<String> lista;

    @FXML
    ObservableList<Comanda> obs_lst = FXCollections.observableArrayList();

    @FXML
    TableView<Comanda> tabel;
    @FXML
    TableColumn<Comanda, String> col_nume_client;
    @FXML
    TableColumn<Comanda, String> col_locatie;
    @FXML
    private TextField durata;
    @FXML
    private DatePicker date_picker;

    private Service service;
    public Long id ;
    public void set_sofer_window(Service service, Long id) {
        this.service=service;
        this.id=id;
        loadComenzi();
    }


    public void loadComenzi(){
        this.service = service;


        col_nume_client.setCellValueFactory(cellData -> {
            Comanda comanda = cellData.getValue();
            Persoana p = service.get_persoana(comanda.getId_client());

            return new SimpleStringProperty(p.getNume());
        });

        col_locatie.setCellValueFactory(cellData -> {
            Comanda comanda = cellData.getValue();
            Client c = service.get_client(comanda.getId_client());

            return new SimpleStringProperty(c.getLocatie());
        });


        tabel.setItems(obs_lst);
        Set<Comanda> utsi = new HashSet<>();

        Iterable<Comanda> a = service.get_all_comenzi();
        for(Comanda co:a)
            if(co.getId_sofer()==0L)
                utsi.add(co);




        List<Comanda> utsl = StreamSupport.stream(utsi.spliterator(), false).toList();
        obs_lst.setAll(utsl);


    }

    public void onoreaza(){
        Comanda c = (Comanda) tabel.getSelectionModel().getSelectedItem();
        String durat = durata.getText();
        service.notifica_clienti(id,c.getId_client(),durat);


    }

    public void afiseaza_clienti(){
        lista.getItems().clear();
        Iterable<Comanda> comenzi= service.get_all_comenzi();
        Set<String> uniqueItems = new HashSet<>();

        for(Comanda c:comenzi){
            if(Objects.equals(c.getId_sofer(), id)){
                Persoana p = service.get_persoana(c.getId_client());
                uniqueItems.add(p.toString());
            }
        }

        lista.getItems().addAll(uniqueItems);
    }

    public void afiseaza_clienti_data(){
        LocalDate localDate = date_picker.getValue();



        lista.getItems().clear();
        Iterable<Comanda> comenzi= service.get_all_comenzi();

        for(Comanda c:comenzi){
            if(Objects.equals(c.getId_sofer(), id)){
                //Persoana p = service.get_persoana(c.getId_client());
                LocalDateTime data_comanda = c.getData();
                LocalDate data_comanda_date = data_comanda.toLocalDate();
                if(localDate.isEqual(data_comanda_date)){
                    String s = service.get_persoana(c.getId_client()).getNume() + " la data de " + c.getData().toLocalDate().toString();
                    String mesaj ="Persoana: " +s;
                    lista.getItems().add(mesaj);

                }


            }
        }




    }

    public void afiseaza_cel_mai_fidel_client(){
        List<Long> lst = new ArrayList<>();

        Iterable<Comanda> comenzi = service.get_all_comenzi();
        for (Comanda c : comenzi) {
            if(Objects.equals(c.getId_sofer(), id)&&c.getId_sofer()!=0L){
                lst.add(c.getId_client());
        }}

        Map<Long, Integer> map = new HashMap<>();

        for (Long t : lst) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<Long, Integer> max = null;

        for (Map.Entry<Long, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        assert max != null;
        Long id_client_fidel = max.getKey();
        Persoana p = service.get_persoana(id_client_fidel);
        lista.getItems().clear();
        String mesaj = "Cel mai fidel client: "+p.toString();
        lista.getItems().add(mesaj);


    }


    public void afiseaza_comenzi_ultimele_3_luni() {
        int k = 0;
        lista.getItems().clear();
        Iterable<Comanda> comenzi = service.get_all_comenzi();

        for (Comanda c : comenzi) {
            if (Objects.equals(c.getId_sofer(), id)) {
                LocalDateTime acum = LocalDateTime.now();

                // Obțineți prima zi a lunii curente și scădeți trei luni
                LocalDate treiLuniInUrmă = acum.toLocalDate().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(3);

                // Obțineți data comenzii
                LocalDateTime dataComanda = c.getData();
                LocalDate dataComandaDate = dataComanda.toLocalDate();

                // Verificați dacă dataComandaDate este după treiLuniInUrmă
                if (dataComandaDate.isAfter(treiLuniInUrmă) || dataComandaDate.isEqual(treiLuniInUrmă)) {
                    //String s = service.get_persoana(c.getId_client()).getNume() + " la data de " + c.getData().toLocalDate().toString();
                    //String mesaj = "Persoana: " + s;
                    //lista.getItems().add(mesaj);
                    k++;
                }

            }
        }


        LocalDate astazi = LocalDate.now();

// Scădeți trei luni pentru a obține data de început a intervalului de trei luni
        LocalDate treiLuniInUrmă = astazi.minusMonths(3);

// Calculați numărul de zile între cele două date
        long numarDeZile = ChronoUnit.DAYS.between(treiLuniInUrmă, astazi);

        float media = (float) k /numarDeZile;
        String mesaj = "Media pe ultimele 3 luni: " + media;
        lista.getItems().add(mesaj);
    }
    }






