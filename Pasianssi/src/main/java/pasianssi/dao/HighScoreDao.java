/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pasianssi.domain.HighScore;

/**
 * @author juliagro
 */
public class HighScoreDao implements Dao<HighScore, Integer> {
    
    private final Database database;
    
    /**
     * Luokan HighScoreDao konstruktori
     * @param database  tietokanta, johon tulokset tallennetaan
     */
    public HighScoreDao(Database database) {
        this.database = database;
    }

    /**
     * Metodi tulosten tietokantaan tallentamiseen
     * @param score tallennettava tulos
     * @throws SQLException
     */
    @Override
    public void save(HighScore score) throws SQLException {
        this.database.update("INSERT INTO HighScore (name, time) VALUES(?,?)", score.getName(), score.getTime());
    }

    /**
     * Metodi kaikkien tulosten katsomiseen
     * @return listan kaikista tietokannan tuloksista
     * @throws SQLException
     */
    @Override
    public List<HighScore> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM HighScore", new HighScoreCollector());
    }
    
    /**
     * Metodi viiden parhaan tuloksen löytämiseen
     * @return  listan viidestä parhaasta tuloksesta
     * @throws SQLException
     */
    public List<HighScore> findFive() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM HighScore ORDER BY time LIMIT 5", new HighScoreCollector());
    }
    
    /**
     * Metodi kertoo kuinka monta tulosta tietokannassa on
     * @return  tietokannan tulosten lukumäärän
     * @throws SQLException
     */
    public Integer howManyScores() throws SQLException {
        List<HighScore> scores = new ArrayList();
        scores = findAll();
        return scores.size();
    }
    
}
