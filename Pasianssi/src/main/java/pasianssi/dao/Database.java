/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.dao;

import java.sql.*;
import java.util.*;

/**
 * Luokka tiedon tallentamiseen tietokantaan
 * @author juliagro
 */
public class Database {

    private final Connection connection;

    /**
     * Luokan Database konstruktori, joka luo tietokannan ja sen ainoan taulun, jos ne eivät ole olemassa
     * @throws Exception
     */
    public Database() throws Exception {
        this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement s = connection.createStatement();
        String st = "CREATE TABLE IF NOT EXISTS HighScore(" + "name varchar(100)," + "time integer);";
        s.executeUpdate(st);
    }

    /**
     * Metodi tietojen keräämiseen tietokannasta
     * @param <T>   tiedonkerääjän määrittävä elementti
     * @param query SQL-lause, jota yritetään suorittaa
     * @param col   tiedonkerääjä
     * @param params    SQL-lauseessa olevat parametrit
     * @return  listan tietokannasta SQL-lauseen mukaisesti kerätyistä riveistä
     * @throws SQLException
     */
    public <T> List<T> queryAndCollect(String query, Collector<T> col, Object... params) throws SQLException {
        List<T> rivit = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rivit.add(col.collect(rs));
                }
            }
        }
        return rivit;
    }

    /**
     * Metodi tietokannan päivittämiseen
     * @param updateQuery   SQL-lause, joka määrää miten tietokantaa päivitetään
     * @param params    SQL-lauseessa olevat parametrit
     * @return
     * @throws SQLException
     */
    public int update(String updateQuery, Object... params) throws SQLException {
        int changes;
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            changes = stmt.executeUpdate();
        }

        return changes;
    }

}
