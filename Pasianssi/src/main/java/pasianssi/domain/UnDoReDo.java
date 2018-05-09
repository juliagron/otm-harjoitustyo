/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

import java.util.ArrayList;
import java.util.List;
import pasianssi.domain.Card.CardGroup;
import pasianssi.ui.PasianssiUi;

/**
 *
 * @author juliagro
 */
public class UnDoReDo {
    private CardStack target;
    private CardStack source;
    private List<Card> moved;
    private PasianssiUi main;

    public UnDoReDo(PasianssiUi main) {
        
        this.main = main;
    }

    public void storingTheMove(CardStack source, CardStack target, List<Card> moved) {
        this.moved = new ArrayList();
        this.source = source;
        this.target = target;
        this.moved.addAll(moved);
    }

    public void unDoMove() {
        if (moved.size() == 1) {
            unDoOneCardMove();
        } else if (source.isTheStackOnTheTable()) {
            unDoTableTableMove();
        } else {
            unDoWasteDeckMove();
        }
    }

    public void reDoMove() {
        if (moved.size() == 1) {
            reDoOneCardMove();
        } else if (source.isTheStackOnTheTable()) {
            reDoTableTableMove();
        } else {
            reDoWasteDeckMove();
        }
    }

    public void unDoOneCardMove() {
        Card card = moved.get(0);
        if (source.isTheStackTheDeck()) {
            card.getGroup().getChildren().clear();
            target.cards().remove(card);
            card.setTheCardFaceUp(false);
            CardGroup cardGroup = new CardGroup(card);
            source.cards().add(card);
            card.setStack(source);
        } else {
            card.getGroup().getChildren().clear();
            target.cards().remove(card);
            CardGroup cardGroup = new CardGroup(card);
            source.cards().add(card);
            card.setStack(source);
        }
    }

    public void reDoOneCardMove() {
        Card card = moved.get(0);
        if (target.isTheStackNextToTheDeck()) {
            card.getGroup().getChildren().clear();
            source.cards().remove(card);
            card.setTheCardFaceUp(true);
            CardGroup cardGroup = new CardGroup(card);
            target.cards().add(card);
            card.setStack(target);
        } else {
            card.getGroup().getChildren().clear();
            source.cards().remove(card);
            CardGroup cardGroup = new CardGroup(card);
            target.cards().add(card);
            card.setStack(target);
        }
    }

    public void unDoTableTableMove() {
        for (int i = 0; i >= moved.size() - 1; i++) {
            Card card = moved.get(i);
            target.removeCardFromTheStack(card);
            source.addCardToTheStack(card);
            CardGroup cardGroup = new  CardGroup(card);
            source.getGroup().getChildren().add(cardGroup);
            main.makeDraggable(card);
            card.setStack(source);
        }
    }

    public void reDoTableTableMove() {
        for (Card card: moved) {
            source.removeCardFromTheStack(card);
            target.addCardToTheStack(card);
            CardGroup cardGroup = new CardGroup(card);
            target.getGroup().getChildren().add(cardGroup);
            main.makeDraggable(card);
            card.setStack(target);
        }
    }

    public void unDoWasteDeckMove() {
        List<Card> copy = new ArrayList();
        copy.addAll(moved);
        for (int i = copy.size() - 1; i >= 0; i--) {
            copy.get(i).setTheCardFaceUp(true);
            target.cards().remove(copy.get(i));
            source.cards().add(copy.get(i));
        }
    }

    public void reDoWasteDeckMove() {
        List<Card> copy = new ArrayList();
        copy.addAll(moved);
        for (int i = copy.size() - 1; i >= 0; i--) {
            copy.get(i).setTheCardFaceUp(false);
            source.cards().remove(copy.get(i));
            target.cards().add(copy.get(i));
        }
    }
    
    public CardStack getTargetStack() {
        return target;
    }
    
    public CardStack getSourceStack() {
        return source;
    }
    
    public List<Card> getMovedCards() {
        return moved;
    }
}
