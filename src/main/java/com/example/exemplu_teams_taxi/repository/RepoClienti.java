package com.example.exemplu_teams_taxi.repository;

import com.example.exemplu_teams_taxi.domain.Client;
import com.example.exemplu_teams_taxi.domain.Persoana;
import com.example.exemplu_teams_taxi.domain.Sofer;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RepoClienti implements Repository<Long, Client>{
    private String url;
    private String username;
    private String password;

    public RepoClienti(String url,String username,String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Client> findOne(Long intID) {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from Client " +
                    "where id = ?");



        ) {
            statement.setLong(1, Math.toIntExact(intID));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {


                String locatie = resultSet.getString("locatie");


                Client s = new Client(intID,locatie);


                return Optional.ofNullable(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
    @Override
    public Iterable<Client> findAll() {
        Set<Client> clienti = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from Client");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");

                String locatie= resultSet.getString("locatie");



                Client s = new Client(id,locatie);


                clienti.add(s);

            }
            return clienti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Client> save(Client entity) {
        Long id = entity.getId();

        String locatie = entity.getLocatie();


        //LocalDateTime data = LocalDateTime.now();
        ////////////Date data = new Date(System.currentTimeMillis());//////////////////////////
        //Iterable<Comanda> comenzi = findAll();


        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO client(id,locatie) VALUES (?,?)")) {

            statement.setLong(1, id);

            statement.setString(2, locatie);




            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Inserare cu succes
            } else {
                // Tratează cazul în care nu a fost inserat niciun rând
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Optional.empty() este returnat indiferent dacă operația a fost cu succes sau nu
        return Optional.empty();


    }

    @Override
    public Optional<Client> delete(Long id){
         try (Connection connection = DriverManager.getConnection(url, username, password);
    PreparedStatement statement = connection.prepareStatement("delete from client where id = ?")) {

        statement.setLong(1, id);



        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            // Inserare cu succes
        } else {
            // Tratează cazul în care nu a fost inserat niciun rând
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    // Optional.empty() este returnat indiferent dacă operația a fost cu succes sau nu
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) {
        return Optional.empty();
    }
}
