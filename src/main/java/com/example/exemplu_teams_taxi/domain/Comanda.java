package com.example.exemplu_teams_taxi.domain;

import java.time.LocalDateTime;

public class Comanda extends Entity<Long>{
    private Long id;
    private Long id_client;
    private Long id_sofer;
    private String locatie;
    private LocalDateTime data;

    public Comanda(Long id,Long id_sofer,Long id_client,LocalDateTime data){
        this.id=id;
        this.id_client=id_client;
        this.id_sofer=id_sofer;
        this.data=data;
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public Long getId_client(){
        return  this.id_client;
    }

    public Long getId(){
        return this.id;
    }
    public Long getId_sofer(){
        return this.id_sofer;
    }

    public String getLocatie(){
        return this.locatie;
    }
    public void setLocatie(String locatie){
        this.locatie=locatie;
    }



}
