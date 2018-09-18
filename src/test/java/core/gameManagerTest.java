package core;

import junit.framework.TestCase;

public class gameManagerTest extends TestCase {
	public void testEnum() {
		GameManager GM = new GameManager();
		// Test Jack
		assertEquals(10, GM.J);
		// Test Queen
		assertEquals(10, GM.Q);
		// Test King
		assertEquals(10, GM.K);
	}
	public void testPlayerHit() {
		GameManager GM = new GameManager();
		// Test if player hits successfully
		GM.playerHit(9);
		assertEquals(9, GM.playerHandValue);
	}
	public void testPlayerHitRepeat() {
		GameManager GM = new GameManager();
		GM.playerHit(9);
		GM.playerHit(8);
		assertEquals(17, GM.playerHandValue);
	}
}
