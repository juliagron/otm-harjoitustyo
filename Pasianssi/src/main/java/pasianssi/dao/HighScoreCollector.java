/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.dao;

import pasianssi.domain.HighScore;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Luokka tiedon keräämiseen SQL-lauseesta
 * @author juliagro
 */
public class HighScoreCollector implements Collector<HighScore> {

    /**
     *  Metodi kerää tietokannasta ResultSetin määrittämät tiedot ja luo niiden perusteella uuden HighScoren
     * @param rs    tietokannasta kerätyt tiedot
     * @return  uuden HighScoren
     * @throws SQLException
     */
    @Override
    public HighScore collect(ResultSet rs) throws SQLException {
        return new HighScore(rs.getString("name"), rs.getInt("time"));
    }
    
}
