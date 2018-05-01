/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juliagro
 */
public class HighScoreTest {
    
    String name;
    String name2;
    String time;
    String time2;
    HighScore score;
    
    public HighScoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        name = "jack";
        name2 = "mary";
        time = "2:45";
        time2 = "1:30";
        score = new HighScore(name, time);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals(name, score.getName());
        assertEquals(time, score.getTime());
    }
    
    @Test
    public void methodsSetSetCorrectValues() {
        score.setName(name2);
        score.setTime(time2);
        assertEquals(name2, score.getName());
        assertEquals(time2, score.getTime());
    }
    
}
