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
 * Luokka pelin aloitustilanteen luomiseen
 * @author juliagro
 */
public class StartingSituation {

    private final List<CardStack> stacks = new ArrayList();
    private final List<Card> all = new ArrayList();
    private final List<Card> usable = new ArrayList();

    /**
     * Luokan StartingSituation konstruktori, se lisää kaikki pinot ja luo korttipakan
     * @param deck      Käsipakka
     * @param waste     Käsipakasta käännetty pino
     * @param endstack1 Lopetuspino
     * @param endstack2 Lopetuspino
     * @param endstack3 Lopetuspino
     * @param endstack4 Lopetuspino
     * @param table1    Pelattava pino
     * @param table2    Pelattava pino
     * @param table3    Pelattava pino
     * @param table4    Pelattava pino
     * @param table5    Pelattava pino
     * @param table6    Pelattava pino
     * @param table7    Pelattava pino
     */
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

    /**
     * Metodi jakaa kortit koordinaattien perusteella joko pöydälle tai käsipakkaan
     */
    public void cardsOnTheStack() {
        stacks.stream().filter((stack) -> (stack.getLocationY() == 1)).forEach((stack) -> {
            howManyCards(stack.getLocationX(), stack);
        });
        usable.stream().forEach((card) -> {
            stacks.get(0).addCardToTheStack(card);
        });
    }

    /**
     * Metodi laskee pinon x-koordinaatin perusteella kuinka monta korttia siihen lisätään ja lisää ne
     * @param x Pinon x-koordinaatti
     * @param stack Pino, johon kortteja lisätään
     */
    public void howManyCards(int x, CardStack stack) {
        int i = x + 1;
        int j = 0;
        while (j < i) {
            stack.addCardToTheStack(all.get(usable.size() - 1));
            usable.remove(usable.size() - 1);
            j++;
        }
    }

    /**
     * Metodi kääntää jokaisen pinon päällimmäisen kortin kuvapuoli ylöspäin
     */
    public void turnFaceUp() {
        stacks.stream().filter((stack) -> (stack.getLocationY() == 1)).forEach((stack) -> {
            stack.topCard().setTheCardFaceUp(true);
        });
    }

    /**
     * Metodi sekoittaa kortit ja jakaa ne aloitustilanteeseen
     */
    public void newDeal() {
        Collections.shuffle(all);
        sameDeal();
    }

    /**
     * Metodi jakaa aloitustilanteen kortit samassa järjestyksessä
     */
    public void sameDeal() {
        usable.clear();
        stacks.stream().forEach((stack) -> {
            stack.emptyStack();
        });
        all.stream().forEach((card) -> {
            card.setTheCardFaceUp(false);
        });
        usable.addAll(all);
        cardsOnTheStack();
        turnFaceUp();
    }

    /**
     * 
     * @return  kaikki kortit pakassa
     */
    public List<Card> getListOfAllCards() {
        return all;
    }

    /**
     * 
     * @return  listan kaikista pinoista pelissä
     */
    public List<CardStack> getListOfAllStacks() {
        return stacks;
    }
}
