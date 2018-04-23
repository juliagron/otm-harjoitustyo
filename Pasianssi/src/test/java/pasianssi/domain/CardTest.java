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
import pasianssi.domain.Card.CardGroup;

import pasianssi.domain.Card.Color;
import pasianssi.domain.Card.Value;

/**
 *
 * @author juliagro
 */
public class CardTest {
    
    public CardTest() {
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
    public void construktorSetsCorrectValues(){
        Card card = new Card(Value.EIGHT,Color.CLUBS);
        assertEquals("EIGHT", card.getValue().toString());
        assertEquals("CLUBS", card.getColor().toString());
    }
    
    @Test
    public void settingTheCardFaceUp(){
        Card card = new Card(Value.ACE, Color.DIAMONDS);
        card.setTheCardFaceUp(true);
        assertTrue(card.isTheCardFaceUp());
    }
    
    @Test
    public void correctToString() {
        Card card = new Card(Value.ACE, Color.CLUBS);
        assertEquals("Card [CLUBS ACE down]", card.toString());
        card.setTheCardFaceUp(true);
        assertEquals("Card [CLUBS ACE up]", card.toString());
    }
    
    @Test
    public void correctStack() {
        CardStack stack = new CardStack(1,1);
        Card card = new Card(Value.ACE, Color.CLUBS);
        card.setStack(stack);
        assertEquals(stack, card.getStack());
    }
    
    @Test
    public void isTheCardBlack() {
        Card card = new Card(Value.ACE, Color.CLUBS);
        Card sec = new Card(Value.ACE,Color.DIAMONDS);
        Card thi = new Card(Value.ACE, Color.SPADES);
        assertTrue(card.getColor().isBlack());
        assertFalse(sec.getColor().isBlack());
        assertTrue(thi.getColor().isBlack());
    }
    
    @Test
    public void cardGroup() {
        Card card = new Card(Value.ACE, Color.CLUBS);
        Card sec = new Card(Value.ACE,Color.DIAMONDS);
        CardGroup group = new CardGroup(card);
        CardGroup secGroup = new CardGroup(sec);
        assertEquals(card, group.getCard());
        group.setCard(sec);
        assertEquals(sec, group.getCard());
        assertEquals(secGroup, sec.getGroup());
        sec.setGroup(group);
        assertEquals(group, sec.getGroup());
    }
}
