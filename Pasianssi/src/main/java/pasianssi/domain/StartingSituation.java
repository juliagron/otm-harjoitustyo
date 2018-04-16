/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pasianssi.domain.Card.Color;
import pasianssi.domain.Card.Value;

/**
 *
 * @author juliagro
 */
public class StartingSituation {
    
    private CardStack deck;
    private CardStack waste;
    private CardStack end1;
    private CardStack end2;
    private CardStack end3;
    private CardStack end4;
    private CardStack table1;
    private CardStack table2;
    private CardStack table3;
    private CardStack table4;
    private CardStack table5;
    private CardStack table6;
    private CardStack table7;
    private List<Card> all = new ArrayList();
    
    
    
    
    public StartingSituation(CardStack stack1, CardStack stack2, CardStack stack3, CardStack stack4, CardStack stack5, CardStack stack6, CardStack stack7, CardStack stack8, CardStack stack9, CardStack stack10, CardStack stack11, CardStack stack12, CardStack stack13) {
        this.deck = stack1;
        this.waste = stack2;
        this.end1 = stack3;
        this.end2 = stack4;
        this.end3 = stack5;
        this.end4 = stack6;
        this.table1 = stack7;
        this.table2 = stack8;
        this.table3 = stack9;
        this.table4 = stack10;
        this.table5 = stack11;
        this.table6 = stack12;
        this.table7 = stack13;
        
        for (Color c: Color.values()) {
            for (Value v: Value.values()) {
                all.add(new Card(v, c));
            }
        }
    }
    
    public void suffle() {
        Collections.shuffle(all);
    }
    
    
}
