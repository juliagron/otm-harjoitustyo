/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

/**
 *
 * @author juliagro
 */
public class Card {
    private Value value;
    private Color color;
    private boolean faceUp;
    
    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Value getValue() {
        return value;
    }
    
    public enum Value {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");
        
        public String str;
        private Value(String str) {
            this.str = str;
        }
    }
    
    public enum Color {
        CLUBS("risti"), SPADES("pata"), HEART("hertta"), DIAMONDS("ruutu");
        
        public String str;
        private Color(String str) {
            this.str = str;
        }
    }
    
    public void setTheCardFaceUp(boolean faceUp){
        this.faceUp = faceUp;
    }
    
    public boolean isTheCardFaceUp(){
        return faceUp;
    }
}
