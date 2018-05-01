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
 * Luokka, joka määrittelee pelissä käytettävät pinot ja niiden kortit
 * @author juliagro
 */
public class CardStack {

    private final int x;
    private final int y;
    private final List<Card> cards;
    private Group group;
    private Group bigGroup;

    /**
     * Luokan CardStack konstruktori, joka asettaa pinolle koordinaatit
     * @param x     Pinon paikkaa x-suunnassa kuvaava muuttuja
     * @param y     Pinon paikkaa y-suunnassa kuvaava muuttuja
     */
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

    /**
     * Metodi kertoo onko pino yksi seitsemästä pelattavasta pinosta
     * @return      true, jos pinon y-koordinaatti on yksi, muulloin false
     */
    public boolean isTheStackOnTheTable() {
        return y == 1;
    }

    /**
     * Metodi kertoo onko pino ns. käsipakka
     * @return      true, jos molemmat koordinaatit ovat nolla, muuten false
     */
    public boolean isTheStackTheDeck() {
        return x == 0 & y == 0;
    }

    /**
     * Metodi kertoo onko pino käsipakasta kännettyjen korttien pino
     * @return      true, jos x-koordinaatti on yksi ja y-koordinaatti on nolla, muuten false
     */
    public boolean isTheStackNextToTheDeck() {
        return x == 1 & y == 0;
    }

    /**
     * Metodi kertoo onko pino yksi neljästä lopetuspinosta
     * @return      true, jos y-koordinaatti on nolla ja x-koordinaatti on yli kaksi, muulloin false
     */
    public boolean isTheStackOneOfTheEndStacks() {
        return y == 0 & x >= 3;
    }

    /**
     * Metodi, jolla kortteja lisätään pinoon
     * @param card      Lisättävä kortti
     */
    public void addCardToTheStack(Card card) {
        this.cards.add(card);
        card.setStack(this);
    }
    
    /**
     * Metodi kertoo pinon päällimäisen kortin
     * @return      pinon päällimäinen kortti
     */
    public Card topCard() {
        return cards.get(cards.size() - 1);
    }
    
    /**
     * Metodi kertoo kuinka monta korttia pinossa on
     * @return      pinon koko
     */
    public int sizeOfTheStack() {
        return cards.size();
    }
    
    /**
     *  Metodi, jolla saadaan kyseisen pinon kortit
     * @return      listan pinon korteista
     */
    public List<Card> cards() {
        return cards;
    }
    
    /**
     * Metodi, joka tyhjentää pinon korteista
     */
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
