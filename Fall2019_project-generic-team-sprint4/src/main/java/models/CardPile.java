package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CardPile {

    //Attributes

    public java.util.List<Card> pile;

    //Methods

    public CardPile() {
        pile = new ArrayList<>();
    }

    //Getter for pile
    public java.util.List<Card> getPile() {
        return pile;
    }

    //Setter for pile
    public void setPile(java.util.List<Card> newPile) {
        pile = newPile;
    }

    //Add card to top of stack
    public void addCard(Card newCard) {
        pile.add(newCard);
    }

    //Return card from top of stack
    @JsonIgnore public Card getTopCard() {
        return pile.get(pile.size()-1);
    }

    //Pop card from top of stack
    public void popTopCard() {
       pile.remove(pile.size() - 1);
    }

    //Return size of card stack
    @JsonIgnore public int getPileSize() {
        return pile.size();
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(pile, new Random(seed));
    }

    public boolean pileHasCards() {
        if (pile.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}