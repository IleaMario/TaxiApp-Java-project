package com.example.exemplu_teams_taxi.repository;


import com.example.exemplu_teams_taxi.domain.Persoana;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RepoPersoane implements Repository<Long, Persoana>{



    private String url;
    private String username;
    private String password;

    public RepoPersoane(String url,String username,String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Persoana> findOne(Long intID) {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from Persoane " +
                    "where id = ?");

        ) {
            statement.setLong(1, Math.toIntExact(intID));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String nume = resultSet.getString("nume");
                String username = resultSet.getString("username");


                Persoana p = new Persoana(intID,username,nume);

                return Optional.ofNullable(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }



    @Override

    public Iterable<Persoana> findAll() {
        Set<Persoana> pers = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from Persoane");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");


                String username = resultSet.getString("username");
                String nume = resultSet.getString("nume");



                Persoana p = new Persoana(id,username,nume);


                pers.add(p);

            }
            return pers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Persoana> save(Persoana entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Persoana> delete(Long integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Persoana> update(Persoana entity) {
        return Optional.empty();
    }


}
