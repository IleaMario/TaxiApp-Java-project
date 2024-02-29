package com.example.exemplu_teams_taxi.repository;

import com.example.exemplu_teams_taxi.domain.Sofer;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class RepoSoferi implements Repository<Long, Sofer>{
    private String url;
    private String username;
    private String password;

    public RepoSoferi(String url,String username,String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Sofer> findOne(Long intID) {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from Sofer " +
                    "where id = ?");

        ) {
            statement.setLong(1, Math.toIntExact(intID));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {


                String indicativ = resultSet.getString("indicativ");


                Sofer s = new Sofer(intID,indicativ);


                return Optional.ofNullable(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
    @Override
    public Iterable<Sofer> findAll() {
        Set<Sofer> soferi = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from Sofer");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");

                String indicativ = resultSet.getString("indicativ");



                Sofer s = new Sofer(id,indicativ);


                soferi.add(s);

            }
            return soferi;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Sofer> save(Sofer entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Sofer> delete(Long integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Sofer> update(Sofer entity) {
        return Optional.empty();
    }
}
