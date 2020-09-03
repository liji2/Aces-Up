package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class testGame {

	@Test
	public void testEnglishGameCreation(){
		Game g = new EnglishGame();
		assertNotNull(g);
	}

	@Test
	public void testEnglishGameBuildDeck(){
		Game g = new EnglishGame();
		g.buildDeck();
		assertEquals(52,g.deck.getPileSize());
	}

	@Test
	public void testEnglishGameStart(){
		Game g = new EnglishGame();
		g.buildDeck();
		g.shuffle();
		g.dealFour();
		assertEquals(1,g.cols.getColumn(0).getPileSize());
		assertEquals(1,g.cols.getColumn(1).getPileSize());
		assertEquals(1,g.cols.getColumn(2).getPileSize());
		assertEquals(1,g.cols.getColumn(3).getPileSize());
	}

	@Test
	public void testEnglishGameRemoveFunction(){
		Game g = new EnglishGame();
		g.buildDeck();

		EnglishCard card1 = new EnglishCard(14, EnglishSuit.Clubs);
		EnglishCard card2 = new EnglishCard(5, EnglishSuit.Clubs);

		g.cols.getColumn(0).addCard(card2);
		g.cols.getColumn(1).addCard(card1);

		g.remove(0);
		assertEquals(0,g.cols.getColumn(0).getPileSize());
	}

	@Test
	public void testEnglishGameMoveFunction(){
		Game g = new EnglishGame();
		EnglishCard card1 = new EnglishCard(14, EnglishSuit.Clubs);
		EnglishCard card2 = new EnglishCard(5, EnglishSuit.Spades);

		g.cols.getColumn(0).addCard(card2);
		g.cols.getColumn(0).addCard(card1);

		assertEquals(2, g.cols.getColumn(0).getPileSize());

		g.move(0,1);

		assertEquals(1, g.cols.getColumn(0).getPileSize());
		assertEquals(1, g.cols.getColumn(1).getPileSize());
	}


	@Test
	public void testSpanishGameCreation(){
		Game g = new SpanishGame();
		assertNotNull(g);
	}

	@Test
	public void testSpanishGameBuildDeck(){
		Game g = new SpanishGame();
		g.buildDeck();
		assertEquals(50,g.deck.getPileSize());
	}

	@Test
	public void testSpanishGameStart(){
		Game g = new SpanishGame();
		g.buildDeck();
		g.shuffle();
		g.dealFour();
		assertEquals(1,g.cols.getColumn(0).getPileSize());
		assertEquals(1,g.cols.getColumn(1).getPileSize());
		assertEquals(1,g.cols.getColumn(2).getPileSize());
		assertEquals(1,g.cols.getColumn(3).getPileSize());
	}

	@Test
	public void testSpanishGameRemoveFunction(){
		Game g = new EnglishGame();
		g.buildDeck();

		SpanishCard card1 = new SpanishCard(11, SpanishSuit.Oros);
		SpanishCard card2 = new SpanishCard(5, SpanishSuit.Oros);

		g.cols.getColumn(0).addCard(card2);
		g.cols.getColumn(1).addCard(card1);

		g.remove(0);
		assertEquals(0,g.cols.getColumn(0).getPileSize());
	}

	@Test
	public void testSpanishGameMoveFunction(){
		Game g = new SpanishGame();
		SpanishCard card1 = new SpanishCard(11, SpanishSuit.Bastos);
		SpanishCard card2 = new SpanishCard(5, SpanishSuit.Oros);

		g.cols.getColumn(0).addCard(card2);
		g.cols.getColumn(0).addCard(card1);

		assertEquals(2, g.cols.getColumn(0).getPileSize());

		g.move(0,1);

		assertEquals(1, g.cols.getColumn(0).getPileSize());
		assertEquals(1, g.cols.getColumn(1).getPileSize());
	}
}