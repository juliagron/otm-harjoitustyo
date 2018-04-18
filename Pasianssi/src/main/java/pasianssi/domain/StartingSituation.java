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

    private List<CardStack> stacks = new ArrayList();
    private List<Card> all = new ArrayList();
    private List<Card> usable = new ArrayList();

    public StartingSituation(CardStack deck, CardStack waste, CardStack endstack1, CardStack endstack2, CardStack endstack3, CardStack endstack4, CardStack table1, CardStack table2, CardStack table3, CardStack table4, CardStack table5, CardStack table6, CardStack table7) {
        stacks.add(deck);
        stacks.add(waste);
        stacks.add(endstack1);
        stacks.add(endstack2);
        stacks.add(endstack3);
        stacks.add(endstack4);
        stacks.add(table1);
        stacks.add(table2);
        stacks.add(table3);
        stacks.add(table4);
        stacks.add(table5);
        stacks.add(table6);
        stacks.add(table7);

        for (Color c : Color.values()) {
            for (Value v : Value.values()) {
                all.add(new Card(v, c));
            }
        }
    }

    public void cardsOnTheStack() {
        for (CardStack stack : stacks) {
            if (stack.getLocationX() == 1) {
                howManyCards(stack.getLocationY(), stack);
            }
        }
        for (Card card: usable) {
            stacks.get(0).addCardToTheStack(card);
        }
    }

    public void howManyCards(int y, CardStack stack) {
        int i = y + 1;
        int j = 0;
        while (j < i) {
            stack.addCardToTheStack(all.get(all.size() - 1 - j));
            usable.remove(all.size() - 1 - j);
            j++;
        }
    }
    
    public void turnFaceUp() {
        for (CardStack stack: stacks) {
            if (stack.getLocationX() == 1) {
                stack.topCard().setTheCardFaceUp(true);
            }
        }
    }

    public void newDeal() {
        Collections.shuffle(all);
        sameDeal();
    }
    
    public void sameDeal() {
        usable = all;
        cardsOnTheStack();
        turnFaceUp();
    }
    
    public List<Card> getListOfAllCards() {
        return all;
    }
    
    public List<CardStack> getListOfAllStacks() {
        return stacks;
    }
}
