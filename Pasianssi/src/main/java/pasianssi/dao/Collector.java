/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Rajapinta, jota tiedonkerääjien tulee noudattaa
 * @author juliagro
 * @param <T>   elementti, jonka tietoja kerätään
 */
public interface Collector<T> {

    /**
     * Abstrakti metodi, joka pitää löytyä kaikista rajapintaa käyttävistä luokista
     * @param rs    tietokannasta kerättävät tiedot
     * @return  uuden kerätyn elementin T
     * @throws SQLException
     */
    T collect(ResultSet rs) throws SQLException;
}
