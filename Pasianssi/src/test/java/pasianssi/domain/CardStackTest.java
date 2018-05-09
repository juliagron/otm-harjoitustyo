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

    CardStack stack1;
    CardStack stack2;
    CardStack stack3;
    CardStack stack4;
    CardStack stack5;
    CardStack stack6;
    Card card;
    Card card1;
    Card card2;

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
        stack1 = new CardStack(0, 1);
        stack2 = new CardStack(5, 1);
        stack3 = new CardStack(6, 0);
        stack4 = new CardStack(0, 0);
        stack5 = new CardStack(1, 0);
        stack6 = new CardStack(1, 1);
        card = new Card(Value.ACE, Color.CLUBS);
        card1 = new Card(Value.EIGHT, Color.DIAMONDS);
        card2 = new Card(Value.FIVE, Color.CLUBS);
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
    public void constructorSetsCorrectLocation() {
        int x = stack1.getLocationX();
        int y = stack1.getLocationY();
        assertEquals(0, x);
        assertEquals(1, y);
    }

    @Test
    public void isTheStackOnTheTableTrue() {
        assertTrue(stack2.isTheStackOnTheTable());
    }

    @Test
    public void isTheStackOnTheTableFalse() {
        assertFalse(stack3.isTheStackOnTheTable());
    }

    @Test
    public void isTheStackTheDeck1() {
        assertTrue(stack4.isTheStackTheDeck());
    }

    @Test
    public void isTheStackTheDeck2() {
        assertFalse(stack3.isTheStackTheDeck());
    }

    @Test
    public void isTheStackTheDeck3() {
        assertFalse(stack1.isTheStackTheDeck());
    }

    @Test
    public void isTheStackNextToTheDeck1() {
        assertTrue(stack5.isTheStackNextToTheDeck());
    }

    @Test
    public void isTheStackNextToTheDeck2() {
        assertFalse(stack3.isTheStackNextToTheDeck());
    }

    @Test
    public void isTheStackNextToTheDeck3() {
        assertFalse(stack6.isTheStackNextToTheDeck());
    }

    @Test
    public void isTheStackOneOfTheEndStacks1() {
        assertTrue(stack3.isTheStackOneOfTheEndStacks());
    }
    
    @Test
    public void isTheStackOneOfTheEndStacks2() {
        assertFalse(stack2.isTheStackOneOfTheEndStacks());
    }
    
    @Test
    public void isTheStackOneOfTheEndStacks3() {
        assertFalse(stack4.isTheStackOneOfTheEndStacks());
    }

    @Test
    public void addingCardsToTheStack() {
        stack6.addCardToTheStack(card);
        Card card1 = stack6.topCard();
        assertEquals(card, card1);
    }

    @Test
    public void sizeOfTheStack() {
        stack6.addCardToTheStack(card);
        stack6.addCardToTheStack(card);
        assertEquals(2, stack6.sizeOfTheStack());
    }

    @Test
    public void listOfCardsInTheStack() {
        stack4.addCardToTheStack(card);
        stack4.addCardToTheStack(card);
        List<Card> cards = new ArrayList();
        cards = stack4.cards();
        assertEquals(2, cards.size());
    }
    @Test
    public void emptyStack() {
        stack4.addCardToTheStack(card);
        stack4.addCardToTheStack(card);
        List<Card> cards = new ArrayList();
        cards = stack4.cards();
        stack4.emptyStack();
        cards.clear();
        cards = stack4.cards();
        assertEquals(0, cards.size());
    }

    @Test
    public void groupsOfTheStack() {
        Group group = new Group();
        Group bigGroup = new Group();
        stack4.setBigGroup(bigGroup);
        stack4.setGroup(group);
        assertEquals(group, stack4.getGroup());
        assertEquals(bigGroup, stack4.getBigGroup());
    }
    
    @Test
    public void topCardOfTheStack1() {
        stack1.addCardToTheStack(card);
        assertEquals(card, stack1.topCard());
    }
    
    @Test
    public void topCardOfTheStack2() {
        assertEquals(null, stack1.topCard());
    }
    
    @Test
    public void removingCardFromTheSack() {
        stack1.addCardToTheStack(card);
        stack1.addCardToTheStack(card1);
        stack1.addCardToTheStack(card2);
        stack1.removeCardFromTheStack(card);
        assertEquals(2, stack1.sizeOfTheStack());
    }
    
    @Test
    public void gettingTheCardsAfterACertainCard() {
        List<Card> rest = new ArrayList();
        stack1.addCardToTheStack(card);
        stack1.addCardToTheStack(card1);
        stack1.addCardToTheStack(card2);
        rest = stack1.getRestAfter(card);
        assertEquals(2, rest.size());
    }

}
