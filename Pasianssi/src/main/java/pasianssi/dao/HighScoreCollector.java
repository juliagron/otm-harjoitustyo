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

import pasianssi.domain.HighScore;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HighScoreCollector implements Collector<HighScore> {

    @Override
    public HighScore collect(ResultSet rs) throws SQLException {
        return new HighScore(rs.getInt("id"), rs.getString("name"), rs.getString("time"));
    }
    
}
