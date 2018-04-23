/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pasianssi.domain.Card.Color;
import pasianssi.domain.Card.Value;

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
    public void isTheStackOnTheTable(){
        CardStack stack = new CardStack(5,1);
        CardStack stack2 = new CardStack(6,0);
        assertTrue(stack.isTheStackOnTheTable());
        assertFalse(stack2.isTheStackOnTheTable());
    }
    
    @Test
    public void isTheStackTheDeck(){
        CardStack stack = new CardStack(0,0);
        CardStack stack2 = new CardStack(6,0);
        CardStack stack3 = new CardStack(0,1);
        assertTrue(stack.isTheStackTheDeck());
        assertFalse(stack2.isTheStackTheDeck());
        assertFalse(stack3.isTheStackTheDeck());
    }
    
    @Test
    public void isTheStackNextToTheDeck(){
        CardStack stack = new CardStack(1,0);
        CardStack stack2 = new CardStack(6,0);
        CardStack stack3 = new CardStack(1,1);
        assertTrue(stack.isTheStackNextToTheDeck());
        assertFalse(stack2.isTheStackNextToTheDeck());
        assertFalse(stack3.isTheStackNextToTheDeck());
    }
    
    @Test
    public void inTheStackOneOfTheEndStacks(){
        CardStack stack = new CardStack(5,0);
        CardStack stack2 = new CardStack(6,1);
        CardStack stack3 = new CardStack(0,0);
        assertTrue(stack.isTheStackOneOfTheEndStacks());
        assertFalse(stack2.isTheStackOneOfTheEndStacks());
        assertFalse(stack3.isTheStackOneOfTheEndStacks());
    }
    
    @Test
    public void addingCardsToTheStack() {
        CardStack stack = new CardStack(1,1);
        Card card = new Card(Value.ACE, Color.CLUBS);
        stack.addCardToTheStack(card);
        Card card1 = stack.topCard();
        assertEquals(card, card1);
    }
    
    @Test
    public void sizeOfTheStack() {
        CardStack stack = new CardStack(1,1);
        stack.addCardToTheStack(new Card(Value.ACE, Color.CLUBS));
        stack.addCardToTheStack(new Card(Value.EIGHT, Color.CLUBS));
        assertEquals(2, stack.sizeOfTheStack());
    }
    
    @Test
    public void listOfCardsInTheStack() {
        CardStack stack = new CardStack(0,0);
        stack.addCardToTheStack(new Card(Value.ACE, Color.CLUBS));
        stack.addCardToTheStack(new Card(Value.EIGHT, Color.CLUBS));
        List<Card> cards = new ArrayList();
        cards = stack.cards();
        assertEquals(2, cards.size());
        stack.emptyStack();
        cards.clear();
        cards = stack.cards();
        assertEquals(0, cards.size());
    }
    
    @Test
    public void groupsOfTheStack() {
        CardStack stack = new CardStack(0,0);
        Group group = new Group();
        Group bigGroup = new Group();
        stack.setBigGroup(bigGroup);
        stack.setGroup(group);
        assertEquals(group, stack.getGroup());
        assertEquals(bigGroup, stack.getBigGroup());
    }
    
    
}
