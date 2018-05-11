/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.dao;

/**
 *
 * @author juliagro
 */

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, K> {
    
    void save(T element) throws SQLException;
    
    List<T> findAll() throws SQLException;
    
}
