/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;

/**
 *
 * @author juliagro
 */
public class CardStack {

    private int x;
    private int y;
    private List<Card> cards;
    private Group group;
    private Group bigGroup;

    public CardStack(int x, int y) {
        this.x = x;
        this.y = y;
        this.cards = new ArrayList();
    }

    public int getLocationX() {
        return x;
    }

    public int getLocationY() {
        return y;
    }

    public boolean isTheStackOnTheTable() {
        if (y == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTheStackTheDeck() {
        if (x == 0 & y == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTheStackNextToTheDeck() {
        if (x == 1 & y == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTheStackOneOfTheEndStacks() {
        if (y == 0 & x >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public void addCardToTheStack(Card card) {
        this.cards.add(card);
        card.setStack(this);
    }
    
    public Card topCard() {
        return cards.get(cards.size() - 1);
    }
    
    public int sizeOfTheStack() {
        return cards.size();
    }
    
    public List<Card> cards() {
        return cards;
    }
    
    public void emptyStack() {
        cards.clear();
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
    
    public void setBigGroup(Group group) {
        this.bigGroup = group;
    }
    
    public Group getBigGroup() {
        return bigGroup;
    }
}
