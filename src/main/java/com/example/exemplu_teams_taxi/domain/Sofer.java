package com.example.exemplu_teams_taxi.domain;


import java.util.Date;

public class Sofer extends Persoana{


    private Long id;
    private String indicativ;

    public void setId(Long id){
        this.id=id;
    }

    public Sofer(Long id,String indicativ){
        super();
        this.id=id;
        this.indicativ=indicativ;
    }

    public String getIndicativ(){
        return this.indicativ;
    }

    public Long getId(){
        return this.id;
    }









}
