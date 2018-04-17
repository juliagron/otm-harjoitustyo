/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.List;
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
        waste = new CardStack(0, 1);
        endstack1 = new CardStack(0, 3);
        endstack2 = new CardStack(0, 4);
        endstack3 = new CardStack(0, 5);
        endstack4 = new CardStack(0, 6);
        table1 = new CardStack(1, 0);
        table2 = new CardStack(1, 1);
        table3 = new CardStack(1, 2);
        table4 = new CardStack(1, 3);
        table5 = new CardStack(1, 4);
        table6 = new CardStack(1, 5);
        table7 = new CardStack(1, 6);
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
    public void suffleSuffles() {
        situation.suffle();
        List<Card> storage = situation.getListOfAllCards();
        int i = 0;
        for (int j = 0; j < cards.size(); j++) {
            if (cards.get(i) == storage.get(i)) {
                i++;
            }
        }
        boolean value = true;
        if (i == 52) {
            value = false;
        }
        assertFalse(value);
    }
}
