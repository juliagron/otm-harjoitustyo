/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import javafx.scene.Group;

/**
 * Luokka, joka määrittelee kortin
 *
 * @author juliagro
 */
public class Card {

    private Value value;
    private Color color;
    private boolean faceUp;
    private CardStack stack;
    private CardGroup group;

    /**
     * Luokan Card konstruktori, joka asettaa kortille värin ja arvon
     *
     * @param value Kortin arvo
     * @param color Kortin väri
     */
    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
        this.faceUp = false;
        CardGroup cardGroup = new CardGroup(this);
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    /**
     * Luokan Card arvon kertova muuttuja
     */
    public enum Value {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

        public String str;

        private Value(String str) {
            this.str = str;
        }
    }

    /**
     * Luokan Card värin kertova muuttuja
     */
    public enum Color {
        CLUBS("♣"), SPADES("♠"), HEARTS("♥"), DIAMONDS("♦");

        public String str;

        private Color(String str) {
            this.str = str;
        }

        /**
         * Muuttujan Color metodi, joka kertoo onko kyseinen kortti väriltään musta vai ei
         * @return      palauttaa true, jos väri on pata tai risti, muuten palauttaa false
         */
        public boolean isBlack() {
            return this == CLUBS || this == SPADES;
        }
    }

    public void setTheCardFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    /**
     * 
     * @return true, jos kortti on kuvapuoli ylöspäin, muuten false
     */
    public boolean isTheCardFaceUp() {
        return faceUp;
    }

    @Override
    public String toString() {
        if (this.isTheCardFaceUp()) {
            return "Card [" + color + " " + value + " up]";
        } else {
            return "Card [" + color + " " + value + " down]";
        }
    }

    public void setStack(CardStack stack) {
        this.stack = stack;
    }

    public CardStack getStack() {
        return stack;
    }
    
    /**
     * Metodi kertoo onko kortti pinonsa päällimmäinen
     * @return  true, js kortti on pinon päälimmäinen, muulloin false
     */
    public boolean isOnTopOfTheStack() {
        if (stack != null) {
            return stack.topCard() == this;
        }
        return false;
    }

    /**
     * Luokan Card sisällä oleva luokka, joka määrittelee kortin muuttujan Group
     */
    public static class CardGroup extends Group {

        private Card card;

        /**
         * Luokan CardGroup konstruktori
         * @param card      Kortti, jonka Group kyseessä on
         */
        public CardGroup(Card card) {
            this.card = card;
            card.setGroup(this);
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }
    }

    public CardGroup getGroup() {
        return this.group;
    }

    public void setGroup(CardGroup group) {
        this.group = group;
    }
}
