package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class testCardPile {

    @Test
    public void testCardPileCreation(){
        CardPile pile = new CardPile();
        assertNotNull(pile);

    }

    @Test
    public void testAddCard(){
        //English Version
        EnglishCard c = new EnglishCard(6, EnglishSuit.Diamonds);
        //Spanish Version
        SpanishCard sc = new SpanishCard(8, SpanishSuit.Bastos);

        //make new card pile
        CardPile p = new CardPile();
        CardPile p2 = new CardPile();

        //add english card to pile created
        p.addCard(c);
        assertEquals(1, p.getPileSize());

        //add spanish card to pile
        p2.addCard(sc);
        assertEquals(1, p2.getPileSize());

    }

    @Test
    public void testGetTopCard(){
        CardPile p = new CardPile();
        CardPile p2 = new CardPile();

        EnglishCard c = new EnglishCard(7, EnglishSuit.Hearts);
        SpanishCard sc = new SpanishCard(8, SpanishSuit.Bastos);

        p.addCard(c);
        p2.addCard(sc);

        assertEquals(c, p.getTopCard());
        assertEquals(sc, p2.getTopCard());
    }

    @Test
    public void testPopTopCard(){
        CardPile p = new CardPile();
        CardPile p2 = new CardPile();

        EnglishCard c = new EnglishCard(7, EnglishSuit.Hearts);
        EnglishCard c2 = new EnglishCard(11, EnglishSuit.Spades);
        SpanishCard sc = new SpanishCard(5, SpanishSuit.Espadas);
        SpanishCard sc2 = new SpanishCard(8, SpanishSuit.Bastos);

        p.addCard(c);
        p.addCard(c2);
        p2.addCard(sc);
        p2.addCard(sc2);

        assertEquals(2, p.getPileSize());
        p.popTopCard();
        assertEquals(1, p.getPileSize());

        assertEquals(2, p2.getPileSize());
        p2.popTopCard();
        assertEquals(1, p2.getPileSize());
    }

    @Test
    public void testGetPileSize(){

        CardPile p = new CardPile();
        CardPile p2 = new CardPile();

        EnglishCard c = new EnglishCard(7,EnglishSuit.Spades);

        p.addCard(c);

        assertEquals(1, p.getPileSize());
        assertEquals(0, p2.getPileSize());

    }


    @Test
    public void testPileHasCards() {

        CardPile p = new CardPile();
        CardPile p2 = new CardPile();

        EnglishCard c = new EnglishCard(7,EnglishSuit.Spades);
        SpanishCard c2 = new SpanishCard(5,SpanishSuit.Espadas);

        assertEquals(false,p.pileHasCards());
        p.addCard(c);
        assertEquals(true,p.pileHasCards());

        assertEquals(false, p2.pileHasCards());
        p2.addCard(c2);
        assertEquals(true,p2.pileHasCards());

    }

}
