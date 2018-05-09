/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.List;
import pasianssi.domain.Card.Value;

/**
 * Luokka pelin säännöille
 * @author juliagro
 */
public class LegalMove {
    
    CardStack target;
    List<Card> moved;

    /**
     * Luokan LegalMove konstruktori
     */
    public LegalMove() {
        moved = new ArrayList();
    }

    /**
     * Metodi kertoo onko siirto sallittu
     * @param tar   Pino johon ollaan siirtämässä kortteja
     * @param mov   Lista siirrettävistä korteista
     * @return      true, jos siirto sallitaan, muuten false
     */
    public boolean isTheMoveLegal(CardStack tar, List mov) {
        this.target = tar;
        this.moved = mov;
        if (target.isTheStackOneOfTheEndStacks()) {
            return endStackRules();
        } else if (target.isTheStackOnTheTable()) {
            return tableStackRules();
        } else {
            return false;
        }
    }

    /**
     * Metodi lopetuspinojen sallittuihin siirtoihin
     * @return  true, jos siirto sallitaan, muuten false
     */
    public boolean endStackRules() {
        if (target.sizeOfTheStack() == 0) {
            return moved.get(0).getValue() == Value.ACE;
        } else {
            return moved.get(0).getValue().ordinal() == target.topCard().getValue().ordinal() + 1 && moved.get(0).getColor() == target.topCard().getColor();
        }
    }

    /**
     * Metodi pöydällä olevien pinojen sallittuihin siirtoihin
     * @return  true, jos siirto sallitaan, muuten false
     */
    public boolean tableStackRules() {
        if (target.sizeOfTheStack() == 0) {
            return moved.get(0).getValue() == Value.KING;
        } else if (moved.get(0).getValue().ordinal() + 1 == target.topCard().getValue().ordinal()) {
            if (target.topCard().getColor().isBlack()) {
                return !moved.get(0).getColor().isBlack();
            } else {
                return moved.get(0).getColor().isBlack();
            }
        } else {
            return false;
        }
    }
}
