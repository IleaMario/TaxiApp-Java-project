package com.example.exemplu_teams_taxi.domain;


import java.util.List;

public class Persoana extends Entity<Long>{
    private Long id;
    private String nume;
    private String username;

    public Persoana(Long id,String usernume,String nume){
        this.id=id;
        this.nume=nume;
        this.username=usernume;
    }



    public Persoana(){
        //
    }
    public String getNume(){
        return this.nume;
    }
    public String getUsername(){
        return this.username;
    }




    public void setId(Long id){
        this.id=id;
    }

    public void setNume(String nume){
        this.nume=nume;
    }

    public void setUsername(String username){
        this.username=username;
    }


    public Long getId(){
        return this.id;
    }



    public String toString(){
        return "Persoana{" +
                this.username + '\'' +
                this.nume + '\'' +

                '}';
    }


}