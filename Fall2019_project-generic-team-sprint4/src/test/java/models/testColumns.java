package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class testColumns {

	@Test
	public void testColumnsCreation() {
		Columns col = new Columns();
		assertNotNull(col);
	}

	@Test
	public void testAddColumn() {
		Columns col = new Columns();
		CardPile pile = new CardPile();
		col.addColumn(pile);
		assertEquals(1, col.columns.size());
	}

	@Test
	public void testRemove() {
		Columns col = new Columns();
		CardPile pile = new CardPile();
		EnglishCard card = new EnglishCard(1, EnglishSuit.Clubs);

		pile.addCard(card);
		col.addColumn(pile);

		assertEquals(1, col.getColumn(0).getPileSize());
		col.remove(0);
		assertEquals(0, col.getColumn(0).getPileSize());
	}

	@Test
	public void testMove() {
		Columns col = new Columns();
		CardPile pile1 = new CardPile();
		CardPile pile2 = new CardPile();
		EnglishCard card1 = new EnglishCard(1, EnglishSuit.Clubs);
		EnglishCard card2 = new EnglishCard(1, EnglishSuit.Spades);

		pile1.addCard(card1);
		pile2.addCard(card2);
		col.addColumn(pile1);
		col.addColumn(pile2);

		assertEquals(1,col.getColumn(0).getPileSize());
		assertEquals(1,col.getColumn(1).getPileSize());

		col.move(0,1);

		assertEquals(0,col.getColumn(0).getPileSize());
		assertEquals(2,col.getColumn(1).getPileSize());

		col.move(1,0);
		col.move(1,0);

		assertEquals(2,col.getColumn(0).getPileSize());
		assertEquals(0,col.getColumn(1).getPileSize());
	}
}