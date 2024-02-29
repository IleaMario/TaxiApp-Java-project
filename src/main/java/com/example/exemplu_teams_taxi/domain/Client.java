package com.example.exemplu_teams_taxi.domain;



import java.util.Date;

public class Client extends Persoana{


    private Long id;
    private String locatie;

    public void setLocatie(String locatie) {
        this.locatie=locatie;
    }

    public void setId(Long id){
        this.id=id;
    }

    public Client(Long id,String locatie){
        super();
        this.id=id;
        this.locatie=locatie;
    }

    public String getLocatie(){
        return this.locatie;
    }

    public Long getId(){
        return this.id;
    }
}
