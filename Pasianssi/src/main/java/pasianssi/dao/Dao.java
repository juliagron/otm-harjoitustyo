/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Rajapinta, jota kaikien tietoja tallentavien luokkien tulee noudattaa 
 * @author juliagro
 * @param <T>
 * @param <K>
 */
public interface Dao<T, K> {
    
    /**
     * Metodi tiedon tallentamiseen tietokantaan
     * @param element   tallennettava elementti
     * @throws SQLException
     */
    void save(T element) throws SQLException;
    
    /**
     * Metodi kaikkien tietojen löytämiseen tietokannasta
     * @return  lista kaikista elementeistä tietokannassa
     * @throws SQLException
     */
    List<T> findAll() throws SQLException;
    
}
