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
import pasianssi.ui.PasianssiUi;

/**
 *
 * @author juliagro
 */
public class UnDoReDoTest {

    private CardStack table1;
    private CardStack table2;
    private CardStack end;
    private CardStack deck;
    private CardStack waste;
    private List<Card> moved;
    private UnDoReDo unre;
    private PasianssiUi main;

    public UnDoReDoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        main = new PasianssiUi();
        unre = new UnDoReDo(main);
        table1 = new CardStack(1, 1);
        table2 = new CardStack(2, 1);
        deck = new CardStack(0, 0);
        waste = new CardStack(1, 0);
        end = new CardStack(4, 0);
        moved = new ArrayList();
        moved.add(new Card(Value.ACE, Color.CLUBS));
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
    public void testingGetTarget() {
        unre.storingTheMove(waste, table1, moved);
        assertEquals(table1, unre.getTargetStack());
    }

    @Test
    public void testingGetSource() {
        unre.storingTheMove(waste, table1, moved);
        assertEquals(waste, unre.getSourceStack());
    }

    @Test
    public void testingGetMovedCards() {
        unre.storingTheMove(waste, table1, moved);
        assertEquals(1, unre.getMovedCards().size());
    }

    @Test
    public void testingUndoMove1() {
        unre.storingTheMove(deck, waste, moved);
        unre.unDoMove();
        assertEquals(deck, moved.get(0).getStack());
    }

    @Test
    public void testingRedoMove1() {
        unre.storingTheMove(deck, waste, moved);
        unre.unDoMove();
        unre.reDoMove();
        assertEquals(waste, moved.get(0).getStack());
    }

    @Test
    public void testingUndoMove2() {
        unre.storingTheMove(table1, end, moved);
        unre.unDoMove();
        assertEquals(table1, moved.get(0).getStack());
    }

    @Test
    public void testingRedoMove2() {
        unre.storingTheMove(table1, end, moved);
        unre.unDoMove();
        unre.reDoMove();
        assertEquals(end, moved.get(0).getStack());
    }

    @Test
    public void random(){
        moved.add(new Card(Value.EIGHT, Color.CLUBS));
        unre.storingTheMove(waste, table1, moved);
        assertEquals(2, unre.getMovedCards().size());
    }
}
