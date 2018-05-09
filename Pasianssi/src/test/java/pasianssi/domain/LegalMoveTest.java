/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.List;
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
public class LegalMoveTest {
    
    private CardStack table;
    private CardStack end;
    private LegalMove move;
    private Card card1;
    private Card card2;
    private Card card3;
    
    public LegalMoveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        move = new LegalMove();
        table = new CardStack(1,1);
        end = new CardStack(4, 0);
        card1 = new Card(Value.ACE, Color.CLUBS);
        card2 = new Card(Value.KING, Color.CLUBS);
        card3 = new Card(Value.QUEEN, Color.DIAMONDS);
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
    public void testingEndStackRules1() {
        List<Card> cards = new ArrayList();
        cards.add(card1);
        assertTrue(move.isTheMoveLegal(end, cards));
    }
    
    @Test
    public void testingEndStackRules2() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.EIGHT, Color.CLUBS));
        assertFalse(move.isTheMoveLegal(end, cards));
    }
    
    @Test
    public void testingEndStackRules3() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.TEN, Color.CLUBS));
        end.addCardToTheStack(card1);
        assertFalse(move.isTheMoveLegal(end, cards));
    }
    
    @Test
    public void testingEndStackRules4() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.TWO ,Color.DIAMONDS));
        end.addCardToTheStack(card1);
        assertFalse(move.isTheMoveLegal(end, cards));
    }
    
    @Test
    public void testingEndStackRules5() {
        List<Card> cards = new ArrayList();
        cards.add(card3);
        end.addCardToTheStack(card1);
        assertFalse(move.isTheMoveLegal(end, cards));
    }
    
    @Test
    public void testingEndStackRules6() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.TWO, Color.CLUBS));
        end.addCardToTheStack(card1);
        assertTrue(move.isTheMoveLegal(end, cards));
    }
    
    @Test
    public void testingTableStackRules1() {
        List<Card> cards = new ArrayList();
        cards.add(card1);
        assertFalse(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingTableStackRules2() {
        List<Card> cards = new ArrayList();
        cards.add(card2);
        assertTrue(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingTableStackRules3() {
        List<Card> cards = new ArrayList();
        cards.add(card1);
        table.addCardToTheStack(card2);
        assertFalse(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingTableStackRules4() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.QUEEN, Color.CLUBS));
        table.addCardToTheStack(card2);
        assertFalse(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingTableStackRules5() {
        List<Card> cards = new ArrayList();
        cards.add(card3);
        table.addCardToTheStack(card2);
        assertTrue(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingTableStackRules6() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.ACE, Color.CLUBS));
        table.addCardToTheStack(card3);
        assertFalse(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingEndStackRules7() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.JACK, Color.CLUBS));
        table.addCardToTheStack(card3);
        assertTrue(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void testingEndStackRules8() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.JACK, Color.HEARTS));
        table.addCardToTheStack(card3);
        assertFalse(move.isTheMoveLegal(table, cards));
    }
    
    @Test
    public void targetStackIsNotEndOrTable() {
        CardStack stack = new CardStack(0,0);
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.JACK, Color.HEARTS));
        assertFalse(move.isTheMoveLegal(stack, cards));
    }
}
