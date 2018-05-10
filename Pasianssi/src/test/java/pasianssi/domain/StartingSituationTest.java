/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
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
public class StartingSituationTest {

    private CardStack deck;
    private CardStack waste;
    private CardStack endstack1;
    private CardStack endstack2;
    private CardStack endstack3;
    private CardStack endstack4;
    private CardStack table1;
    private CardStack table2;
    private CardStack table3;
    private CardStack table4;
    private CardStack table5;
    private CardStack table6;
    private CardStack table7;
    private StartingSituation situation;
    private List<Card> cards;
    private List<CardStack> stacks;

    public StartingSituationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        deck = new CardStack(0, 0);
        waste = new CardStack(1, 0);
        endstack1 = new CardStack(3, 0);
        endstack2 = new CardStack(4, 0);
        endstack3 = new CardStack(5, 0);
        endstack4 = new CardStack(6, 0);
        table1 = new CardStack(0, 1);
        table2 = new CardStack(1, 1);
        table3 = new CardStack(2, 1);
        table4 = new CardStack(3, 1);
        table5 = new CardStack(4, 1);
        table6 = new CardStack(5, 1);
        table7 = new CardStack(6, 1);
        situation = new StartingSituation(deck, waste, endstack1, endstack2, endstack3, endstack4, table1, table2, table3, table4, table5, table6, table7);
        cards = situation.getListOfAllCards();
        stacks = situation.getListOfAllStacks();
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
    public void gettingListOfCards() {
        assertEquals(52, cards.size());
    }

    @Test
    public void gettingListOfAllTheStacks() {
        assertEquals(13, stacks.size());
    }

    @Test
    public void turningTopCardFaceUp1() {
        situation.newDeal();
        boolean faceup = true;
        for (CardStack stack : stacks) {
            if (stack.isTheStackOnTheTable()) {
                if (!stack.topCard().isTheCardFaceUp()) {
                    faceup = false;
                }
            }
        }
        assertTrue(faceup);
    }

    @Test
    public void turningTheCardFaceUp2() {
        situation.newDeal();
        boolean faceup = false;
        for (CardStack stack : stacks) {
            for (int i = 0; i < stack.sizeOfTheStack() - 2; i++) {
                faceup = stack.cards().get(i).isTheCardFaceUp();
            }
        }
        assertFalse(faceup);
    }
    
    @Test
    public void correctNumberOfCardsOnTheStack() {
        situation.newDeal();
        boolean controlValue = true;
        for (CardStack stack: stacks) {
            if (stack.isTheStackNextToTheDeck() || stack.isTheStackOneOfTheEndStacks()) {
                if (stack.sizeOfTheStack() != 0) {
                    controlValue = false;
                }
            } else if (stack.isTheStackTheDeck()) {
                if (stack.sizeOfTheStack() != 24) {
                    controlValue = false;
                }
            } else if (stack.isTheStackOnTheTable()) {
                if (stack.getLocationX() + 1 != stack.sizeOfTheStack()) {
                    controlValue = false;
                }
            }
        }
        assertTrue(controlValue);
    }
    
    @Test
    public void sameDealDoesNotShuffleTheDeck() {
        List<Card> temp = new ArrayList();
        temp.addAll(cards);
        situation.sameDeal();
        assertEquals(temp, cards);
    }

    @Test
    public void newDealShufflesTheDeck() {
        List<Card> temp = new ArrayList();
        temp.addAll(cards);
        situation.newDeal();
        assertThat(temp, IsNot.not(IsEqual.equalTo(cards)));
    }

}
