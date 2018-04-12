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
public class CardStack {
    
    private int x;
    private int y;
    
    public CardStack(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getLocationX() {
        return x;
    }
    
    public int getLocationY() {
        return y;
    }
    
    public boolean isTheStackOnTheTable() {
        if(x == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isTheStackTheDeck(){
        if(x == 0 & y == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isTheStackNextToTheDeck(){
        if(x == 0 & y == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isTheStackOneOfTheEndStacks(){
        if(x == 0 & y >= 3){
            return true;
        } else {
        return false;
    }
    }
    
}
