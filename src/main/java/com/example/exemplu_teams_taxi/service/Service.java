package com.example.exemplu_teams_taxi.service;

import com.example.exemplu_teams_taxi.controller.ClientWindow;
import com.example.exemplu_teams_taxi.controller.SoferWindow;
import com.example.exemplu_teams_taxi.domain.*;
import com.example.exemplu_teams_taxi.repository.RepoClienti;
import com.example.exemplu_teams_taxi.repository.RepoComenzi;
import com.example.exemplu_teams_taxi.repository.RepoPersoane;
import com.example.exemplu_teams_taxi.repository.RepoSoferi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Service<ID,E extends Entity<ID>> {
    private RepoPersoane repo_persoane;
    private RepoSoferi repo_soferi;
    private RepoComenzi repo_comenzi;
    private RepoClienti repo_clienti;
    private List<SoferWindow> ferestreSoferi = new ArrayList<>();
    private List<ClientWindow> ferestreClienti = new ArrayList<>();

    //private List<SoferWindow> ferestreSoferi = new ArrayList<>();
    //private List<ClientWindow> ferestreClienti = new ArrayList<>();



    public Service(RepoPersoane repo_persoane, RepoSoferi repo_soferi, RepoClienti repo_clienti,RepoComenzi repo_comenzi) {
        this.repo_persoane = repo_persoane;
        this.repo_soferi = repo_soferi;
        this.repo_clienti = repo_clienti;
        this.repo_comenzi = repo_comenzi;
    }

    public Iterable<Sofer> get_all_soferi(){
        return repo_soferi.findAll();
    }
    public Iterable<Client> get_all_clienti(){return repo_clienti.findAll();}
    public Iterable<Comanda> get_all_comenzi(){return repo_comenzi.findAll();}
    public Iterable<Persoana> get_all_persoane(){return repo_persoane.findAll();}

    public Optional<Persoana> get_persoana_dupa_username(String us){
        Iterable<Persoana> persoane =  get_all_persoane();
        for(Persoana p:persoane){
            if(Objects.equals(p.getUsername(), us)){
                Optional<Persoana> pers=repo_persoane.findOne(p.getId());
                return pers;
            }
        }


        return Optional.empty();
    }

    public String sofer_sau_client(Long id){
        Iterable<Sofer> soferi =  get_all_soferi();
        for(Sofer s:soferi){
            if(Objects.equals(s.getId(), id))
                return "sofer";
        }

        Iterable<Client> clienti =  get_all_clienti();
        for(Client s:clienti){
            if(Objects.equals(s.getId(), id))
                return "client";
        }
        return "niciuna";

    }




    public void adauga_fereastra_soferi(SoferWindow dialogStage) {
        ferestreSoferi.add(dialogStage);


    }
    public Iterable<SoferWindow> get_ferestre_soferi(){
        return this.ferestreSoferi;
    }

    public void adauga_fereastra_clienti(ClientWindow dialogStage) {
        ferestreClienti.add(dialogStage);


    }
    public Iterable<ClientWindow> get_ferestre_clienti(){
        return this.ferestreClienti;
    }

    public Client get_client(Long id) {
        Optional<Client> client= repo_clienti.findOne(id);
        Client c = client.get();
        return c;

    }

    public Persoana get_persoana(Long id){
        Optional<Persoana> pers = repo_persoane.findOne(id);
        Persoana persoana = pers.get();
        return persoana;
    }

    public Comanda get_comanda_dupa_id_client(Long id_client){
        for(Comanda c:get_all_comenzi())
            if(Objects.equals(c.getId_client(), id_client))
                return c;
        return null;
    }
    public void sterge_client(Long id){
        repo_clienti.delete(id);
    }
    public void adauga_client(Client client){
        repo_clienti.save(client);
    }

    public void adauga_comanda(Comanda comanda){
        repo_comenzi.save(comanda);
    }
    public void sterge_comanda(Long id){
        repo_comenzi.delete(id);
    }

    public void notifica_soferi() {
        Iterable<SoferWindow> sof=ferestreSoferi;
        for(SoferWindow s:sof){
            s.loadComenzi();
        }
    }

    public void notifica_clienti(Long id_sofer,Long id_client, String durata) {
        Persoana p = get_persoana(id_sofer);

        Iterable<ClientWindow> cli=ferestreClienti;
        for(ClientWindow s:cli){
            if(Objects.equals(s.id, id_client))
                s.showInfo(p.getNume(),durata,id_sofer);
        }
    }





}



