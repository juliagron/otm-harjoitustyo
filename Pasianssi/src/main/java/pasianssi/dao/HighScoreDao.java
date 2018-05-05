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
import java.util.ArrayList;
import java.util.List;
import pasianssi.domain.HighScore;

public class HighScoreDao implements Dao<HighScore, Integer> {
    
    private final Database database;
    
    public HighScoreDao(Database database) {
        this.database = database;
    }

    @Override
    public void save(HighScore score) throws SQLException {
        this.database.update("INSERT INTO HighScore (name, time) VALUES(?,?)", score.getName(), score.getTime());
    }

    @Override
    public List<HighScore> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM HighScore", new HighScoreCollector());
    }

    @Override
    public void delete(Integer key) throws SQLException {
        this.database.update("DELETE FROM HighScore WHERE id = ?", key);
    }
    
    public List<HighScore> findFive() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM HighScore ORDER BY time LIMIT 5", new HighScoreCollector());
    }
    
    public Integer howManyScores() throws SQLException {
        List<HighScore> scores = new ArrayList();
        scores = findAll();
        return scores.size();
    }
    
}
