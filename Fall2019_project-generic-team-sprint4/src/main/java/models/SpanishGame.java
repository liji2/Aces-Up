package models;

public class SpanishGame extends Game {

    public SpanishGame(){
        score = 0;
        ver = 1;
        cardsLeft = 50;
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

    public void buildDeck() {
        for (int i = 2; i < 14; i++) {
            deck.addCard(new SpanishCard(i, SpanishSuit.Bastos));
            deck.addCard(new SpanishCard(i, SpanishSuit.Oros));
            deck.addCard(new SpanishCard(i, SpanishSuit.Copas));
            deck.addCard(new SpanishCard(i, SpanishSuit.Espadas));
        }
        deck.addCard(new SpanishCard(13, SpanishSuit.Jokers));
        deck.addCard(new SpanishCard(13, SpanishSuit.Jokers));
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
        else if (canRemoveJoker(columnNumber)) {
            Card toRemove = cols.getColumn(columnNumber).getTopCard();
            Card joker = removeJoker();
            cols.remove(columnNumber);

            discardPile.addCard(toRemove);
            discardPile.addCard(joker);
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

    //Check if the top card of the column can be removed by a joker (anything but an ace)
    private boolean canRemoveJoker(int columnNumber) {
        if (cols.getColumn(columnNumber).getTopCard().getValue() < 14) {
            for (int i = 0; i < 4; i++) {
                if (cols.getColumn(i).getTopCard().getSuit() == SpanishSuit.Jokers) {
                    return true;
                }
            }
        }
        return false;
    }

    //Remove a joker card from the top of any column.
    private Card removeJoker() {
        for (int i = 0; i < 4; i++) {
            if (cols.getColumn(i).getTopCard().getSuit() == SpanishSuit.Jokers) {
                Card toRemove = cols.getColumn(i).getTopCard();
                cols.remove(i);
                return toRemove;
            }
        }
        return null;
    }

    //Check if a move is valid and call the cols.move() method if it is
    public void move(int columnFrom, int columnTo) {
        if (!cols.getColumn(columnTo).pileHasCards()) {
            cols.move(columnFrom, columnTo);
        }
    }
}