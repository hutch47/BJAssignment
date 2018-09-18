package core;

import junit.framework.TestCase;

public class gameManagerTest extends TestCase {
	GameManager GM = new GameManager();
	public void testEnum() {
		// Test Jack
		assertEquals(10, GM.J);
		// Test Queen
		assertEquals(10, GM.Q);
		// Test King
		assertEquals(10, GM.K);
	}
}
