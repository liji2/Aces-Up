package models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*
** Game class manages the actions of the game: removing, moving, shuffling, and dealing cards.
**
*/
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
abstract public class Game {

    /*
    	Attributes
    */

    public Columns cols;

    public CardPile deck;

    public CardPile discardPile;

    public boolean check;

    public int score;

    public int cardsLeft;

    // 0 = Standard, 1 = Spanish
    public int ver;
    /*
    	Methods
    */

    // initialize a new game such that each column can store cards
    public Game(){
        score = 0;
        ver = 0;
        cardsLeft = 0;
        check = false;

        cols = new Columns();
        deck = new CardPile();
        discardPile = new CardPile();

        //Add four columns to the Columns object
        cols.addColumn(new CardPile());
        cols.addColumn(new CardPile());
        cols.addColumn(new CardPile());
        cols.addColumn(new CardPile());
    }

    //Add all 52 cards to the deck
    //Implemented differently in child classes
    public abstract void buildDeck();

    //Shuffle the deck
    public void shuffle() {
        deck.shuffle();
    }

    //Deal four cards from the deck, once to each column
    public void dealFour() {
        for(int i = 0; i < 4; i++){
        	if(deck.pileHasCards()) {
				cols.getColumn(i).addCard(deck.getTopCard());
				deck.popTopCard();
				updateStats();
			}
        }
    }

    //Remove a card from a column, also checking if it can be removed
    public abstract void remove(int columnNumber);

    // Move an Ace card from a column, to another empty column
    public abstract void move(int columnFrom, int columnTo);

    //Update score and cards left
    public void updateStats() {
        cardsLeft = deck.getPileSize();
        score = discardPile.getPileSize();
    }
}