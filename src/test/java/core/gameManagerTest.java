package core;

import junit.framework.TestCase;

public class gameManagerTest extends TestCase {
	public void testEnum() {
		GameManager GM = new GameManager();
		// Test Jack
		assertEquals(10, Royal.J.getValue());
		// Test Queen
		assertEquals(10, Royal.Q.getValue());
		// Test King
		assertEquals(10, Royal.K.getValue());
	}
	public void testPlayerHit() {
		GameManager GM = new GameManager();
		// Test if player hits successfully
		GM.playerHit(9);
		assertEquals(9, GM.getPlayerHandValue());
	}
	public void testPlayerHitRepeat() {
		GameManager GM = new GameManager();
		GM.playerHit(9);
		GM.playerHit(8);
		assertEquals(17, GM.getPlayerHandValue());
	}
	public void testPlayerTwoAce() {
		GameManager GM = new GameManager();
		GM.playerDeal("HA", "DA");
		GM.playerHit(10);
		assertEquals(17, GM.getPlayerHandValue());
	}
	public void testPlayerBlackjack() {
		GameManager GM = new GameManager();
		
		// playerHit will return a 0 if under 21, a -1 if bust, and a 1 if 21
		// playerDeal will return a 1 if blackjack, 0 otherwise
		assertEquals(1, GM.playerDeal("SA", "HJ"));
	}
	public void testDealerBlackjack() {
		GameManager GM = new GameManager();
		
		assertEquals(1, GM.dealerDeal("DA", "DJ"));
	}
	public void testDealerWinBlackJack() {
		GameManager GM = new GameManager();
		int p, d;
		p = GM.playerDeal("DJ", "DQ");
		d = GM.dealerDeal("HJ", "DA");
		assertEquals(-1, p - d);
	}
	public void testPlayerWinBlackjack() {
		GameManager GM = new GameManager();
		int p, d;
		p = GM.playerDeal("DJ", "DA");
		d = GM.dealerDeal("HJ", "DQ");
		assertEquals(1, p - d);
	}
}
