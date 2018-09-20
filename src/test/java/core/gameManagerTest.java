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
		GM.playerHit("H9");
		assertEquals(9, GM.getPlayerHandValue());
	}
	public void testPlayerHitRepeat() {
		GameManager GM = new GameManager();
		GM.playerHit("H9");
		GM.playerHit("S8");
		assertEquals(17, GM.getPlayerHandValue());
	}
	public void testPlayerTwoAce() {
		GameManager GM = new GameManager();
		GM.playerDeal("HA", "DA");
		GM.playerHit("H10");
		assertEquals(12, GM.getPlayerHandValue());
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
		GM.playerDeal("DJ", "HQ");
		GM.dealerDeal("HJ", "DA");
		assertEquals(true, GM.dealerWin());
	}
	public void testPlayerWinBlackjack() {
		GameManager GM = new GameManager();
		GM.playerDeal("DJ", "DA");
		GM.dealerDeal("HJ", "DQ");
		assertEquals(false, GM.dealerWin());
	}
	public void testDealerBust() {
		GameManager GM = new GameManager();
		GM.playerDeal("DJ", "DQ");
		GM.dealerDeal("HJ", "D5");
		GM.dealerHit("C7");
		assertEquals(false, GM.dealerWin());
	}
	public void testPlayerBust() {
		GameManager GM = new GameManager();
		GM.playerDeal("DJ", "DQ");
		GM.dealerDeal("HJ", "D5");
		GM.playerHit("HQ");
		assertEquals(true, GM.dealerWin());
	}
	public void testDraw() {
		GameManager GM = new GameManager();
		GM.playerDeal("DJ", "DQ");
		GM.dealerDeal("HJ", "SQ");
		assertEquals(true, GM.dealerWin());
	}
}
