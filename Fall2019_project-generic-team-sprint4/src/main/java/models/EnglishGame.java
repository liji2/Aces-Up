package models;

public class EnglishGame extends Game {

    public EnglishGame(){
        score = 0;
        ver = 0;
        cardsLeft = 52;
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

    //Build the deck for the start of the game
    public void buildDeck() {
        for (int i = 2; i < 15; i++) {
            deck.addCard(new EnglishCard(i, EnglishSuit.Clubs));
            deck.addCard(new EnglishCard(i, EnglishSuit.Hearts));
            deck.addCard(new EnglishCard(i, EnglishSuit.Diamonds));
            deck.addCard(new EnglishCard(i, EnglishSuit.Spades));
        }
    }

    //Check if a removal is valid and call the cols.remove() method if it is
    public void remove(int columnNumber) {
		check = true;
        if (canRemove(columnNumber)) {
			Card toRemove = cols.getColumn(columnNumber).getTopCard();
			cols.remove(columnNumber);
			discardPile.addCard(toRemove);
			check = false;
		}
        updateStats();
    }

    //Check if a card can be removed from the given column - used in remove()
    private boolean canRemove(int columnNumber) {
        Card c = cols.getColumn(columnNumber).getTopCard();
        for (int i = 0; i < 4; i++) {
            if (i != columnNumber) {
                if (cols.getColumn(i).pileHasCards()) {
                    Card compare = cols.getColumn(i).getTopCard();
                    if (compare.getSuit() == c.getSuit()) {
                        if (compare.getValue() > c.getValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //Check if a move is valid and call the cols.move() method if it is
    public void move(int columnFrom, int columnTo) {
		if (!cols.getColumn(columnTo).pileHasCards()) {
			Card cardToMove = cols.getColumn(columnFrom).getTopCard();
			String cname = cardToMove.toString();
			if ((cname.equals("14Diamonds")) || (cname.equals("14Clubs")) || (cname.equals("14Hearts")) || (cname.equals("14Spades"))) {
				cols.move(columnFrom, columnTo);
			}
		}
	}
}