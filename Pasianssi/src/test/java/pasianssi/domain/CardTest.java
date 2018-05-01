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
    
    Card card;
    Card card1;
    Card card2;
    Value value;
    Color color;
    
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
        card = new Card(Value.ACE, Color.CLUBS);
        card1 = new Card(Value.ACE, Color.DIAMONDS);
        card2 = new Card(Value.ACE, Color.SPADES);
        value = Value.ACE;
        color = Color.CLUBS;
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
    public void getterReturnsCorrectValue(){
        assertEquals(value, card.getValue());
    }
    
    @Test
    public void getterReturnsCorrectColor() {
        assertEquals(color, card.getColor());
    }
    
    @Test
    public void toStringValue() {
        assertEquals("ACE", card.getValue().toString());
    }
    
    @Test
    public void toStringColor() {
        assertEquals("CLUBS", card.getColor().toString());
    }
    
    @Test
    public void settingTheCardFaceUp(){
        card.setTheCardFaceUp(true);
        assertTrue(card.isTheCardFaceUp());
    }
    
    @Test
    public void correctToString() {
        assertEquals("Card [CLUBS ACE down]", card.toString());
        card.setTheCardFaceUp(true);
        assertEquals("Card [CLUBS ACE up]", card.toString());
    }
    
    @Test
    public void correctStack() {
        CardStack stack = new CardStack(1,1);
        card.setStack(stack);
        assertEquals(stack, card.getStack());
    }
    
    @Test
    public void isTheCardBlack1() {
        assertTrue(card.getColor().isBlack()); 
    }
    
    @Test
    public void isTheCardBlack2() {
        assertFalse(card1.getColor().isBlack());
    }
    
    @Test
    public void isTheCardBlack3() {
        assertTrue(card2.getColor().isBlack());
    }
    
    @Test
    public void cardGroup() {
        CardGroup group = new CardGroup(card);
        CardGroup secGroup = new CardGroup(card1);
        assertEquals(card, group.getCard());
        group.setCard(card1);
        assertEquals(card1, group.getCard());
        assertEquals(secGroup, card1.getGroup());
        card1.setGroup(group);
        assertEquals(group, card1.getGroup());
    }
}
