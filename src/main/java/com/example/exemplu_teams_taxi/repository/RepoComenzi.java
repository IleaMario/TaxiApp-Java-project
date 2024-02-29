package com.example.exemplu_teams_taxi.repository;

import com.example.exemplu_teams_taxi.domain.Client;
import com.example.exemplu_teams_taxi.domain.Comanda;
import com.example.exemplu_teams_taxi.domain.Persoana;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RepoComenzi implements Repository<Long, Comanda>{
    private String url;
    private String username;
    private String password;

    public RepoComenzi(String url,String username,String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Comanda> findOne(Long aLong) {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from Comenzi " +
                    "where id_comanda = ?");

        ) {
            statement.setLong(1, Math.toIntExact(aLong));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Long id_sofer = resultSet.getLong("id_sofer");
                Long id_client = resultSet.getLong("id_client");
                LocalDateTime data = resultSet.getTimestamp("data").toLocalDateTime();


                Comanda c = new Comanda(aLong,id_sofer,id_client,data);

                return Optional.ofNullable(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.empty();
    }

    @Override
    public Iterable<Comanda> findAll() {
        Set<Comanda> comenzi = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from Comenzi");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id_comanda");
                Long id_sofer = resultSet.getLong("id_sofer");
                Long id_client = resultSet.getLong("id_client");
                LocalDateTime data = resultSet.getTimestamp("data").toLocalDateTime();;



                Comanda c=new Comanda(id,id_sofer,id_client,data);


                comenzi.add(c);

            }
            return comenzi;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Comanda> save(Comanda entity) {
        Long id_comanda = entity.getId();
        Long id_sofer = entity.getId_sofer();
        Long id_client = entity.getId_client();
        LocalDateTime data = entity.getData();


        //LocalDateTime data = LocalDateTime.now();
        ////////////Date data = new Date(System.currentTimeMillis());//////////////////////////
        //Iterable<Comanda> comenzi = findAll();


        try (Connection connection = DriverManager.getConnection(url, username, password);

             PreparedStatement statement = connection.prepareStatement("INSERT INTO comenzi (id_comanda, id_sofer, id_client, data) VALUES (?, ?, ?, ?)");
        ){

            statement.setLong(1, id_comanda);
            statement.setLong(2, id_sofer);

            statement.setLong(3, id_client);
            statement.setTimestamp(4, Timestamp.valueOf(data));



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
    public Optional<Comanda> delete(Long id_comanda){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from comenzi where id_comanda = ?")) {

            statement.setLong(1, id_comanda);

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
    public Optional<Comanda> update(Comanda entity) {
        return Optional.empty();
    }
}
