package core;

import org.junit.Test;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	public void testDeck() {
		GameManager GM = new GameManager();
		assertEquals(52, GM.deck.size());
	}
}
