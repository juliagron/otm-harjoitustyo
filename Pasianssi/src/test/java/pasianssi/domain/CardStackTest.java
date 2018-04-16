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
public class CardStackTest {
    
    public CardStackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
    public void constructorSetsCorrectLocation(){
        CardStack stack = new CardStack(0,1);
        int x = stack.getLocationX();
        int y = stack.getLocationY();
        assertEquals(0, x);
        assertEquals(1, y);
    }
    
    @Test
    public void inTheStackOnTheTable(){
        CardStack stack = new CardStack(1,5);
        CardStack stack2 = new CardStack(0,6);
        assertTrue(stack.isTheStackOnTheTable());
        assertFalse(stack2.isTheStackOnTheTable());
    }
    
    @Test
    public void inTheStackTheDeck(){
        CardStack stack = new CardStack(0,0);
        CardStack stack2 = new CardStack(0,6);
        CardStack stack3 = new CardStack(1,0);
        assertTrue(stack.isTheStackTheDeck());
        assertFalse(stack2.isTheStackTheDeck());
        assertTrue(stack3.isTheStackTheDeck());
    }
    
    @Test
    public void inTheStackNextToTheDeck(){
        CardStack stack = new CardStack(0,1);
        CardStack stack2 = new CardStack(0,6);
        assertTrue(stack.isTheStackNextToTheDeck());
        assertFalse(stack2.isTheStackNextToTheDeck());
    }
    
    @Test
    public void inTheStackOneOfTheEndStacks(){
        CardStack stack = new CardStack(0,5);
        CardStack stack2 = new CardStack(1,6);
        assertTrue(stack.isTheStackOneOfTheEndStacks());
        assertFalse(stack2.isTheStackOneOfTheEndStacks());
    }
}
