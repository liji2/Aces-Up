package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelhilton on 1/26/16.
 */
public class testCard {

    @Test
    public void testCardCreation() {
        EnglishCard c = new EnglishCard(3,EnglishSuit.Hearts);
        assertNotNull(c);

        SpanishCard sc = new SpanishCard(6,SpanishSuit.Bastos);
        assertNotNull(sc);
    }

    @Test
    public void testGetSuit() {
        EnglishCard c = new EnglishCard(5,EnglishSuit.Clubs);
        assertEquals(EnglishSuit.Clubs,c.getSuit());

        SpanishCard sc = new SpanishCard(13,SpanishSuit.Jokers);
        assertEquals(SpanishSuit.Jokers,sc.getSuit());
    }

    @Test
    public void testGetValue() {
        EnglishCard c = new EnglishCard(10,EnglishSuit.Diamonds);
        assertEquals(10,c.getValue());

        SpanishCard sc = new SpanishCard(9,SpanishSuit.Copas);
        assertEquals(9,sc.getValue());
    }

    @Test
    public void testToString() {
        EnglishCard c = new EnglishCard(14,EnglishSuit.Spades);
        assertEquals("14Spades",c.toString());

        SpanishCard sc = new SpanishCard(13,SpanishSuit.Espadas);
        assertEquals("13Espadas",sc.toString());
    }

    @Test
    public void testMove() {
        Columns col = new Columns();
        CardPile cp1 = new CardPile();
        CardPile cp2 = new CardPile();
        EnglishCard c1 = new EnglishCard(5,EnglishSuit.Diamonds);
        EnglishCard c2 = new EnglishCard(10,EnglishSuit.Clubs);

        cp1.addCard(c1);
        cp2.addCard(c2);
        col.addColumn(cp1);
        col.addColumn(cp2);
        col.move(0,1);

        assertEquals(c1,cp2.getTopCard());
    }

    @Test
    public void testMoveSP() {
        Columns col = new Columns();
        CardPile cp1 = new CardPile();
        CardPile cp2 = new CardPile();
        SpanishCard c1 = new SpanishCard(5,SpanishSuit.Espadas);
        SpanishCard c2 = new SpanishCard(10,SpanishSuit.Bastos);

        cp1.addCard(c1);
        cp2.addCard(c2);
        col.addColumn(cp1);
        col.addColumn(cp2);
        col.move(0,1);

        assertEquals(c1,cp2.getTopCard());

    }

    @Test
    public void testRemove() {
        Columns col = new Columns();
        CardPile cp1 = new CardPile();
        CardPile cp2 = new CardPile();
        EnglishCard c1 = new EnglishCard(2,EnglishSuit.Spades);
        EnglishCard c2 = new EnglishCard(11,EnglishSuit.Diamonds);

        cp1.addCard(c1);
        cp2.addCard(c2);
        col.addColumn(cp1);
        col.addColumn(cp2);
        col.move(0,1);
        col.remove(1);

        assertEquals(c2,cp2.getTopCard());
    }

    @Test
    public void testRemoveSP() {
        Columns col = new Columns();
        CardPile cp1 = new CardPile();
        CardPile cp2 = new CardPile();
        SpanishCard c1 = new SpanishCard(6,SpanishSuit.Oros);
        SpanishCard c2 = new SpanishCard(13,SpanishSuit.Jokers);

        cp1.addCard(c1);
        cp2.addCard(c2);
        col.addColumn(cp1);
        col.addColumn(cp2);
        col.move(0,1);
        col.remove(1);

        assertEquals(c2,cp2.getTopCard());
    }


}
