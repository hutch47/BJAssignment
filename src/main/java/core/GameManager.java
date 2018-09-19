package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
	// Variable definition
	CardHolder player;
	CardHolder dealer;
	List<String> deck;
	Random rand;
	// Constructor
	GameManager() {
		// Initialize variables 
		deck = new ArrayList<>();
		player = new CardHolder();
		dealer = new CardHolder(true);
		rand = new Random();
		
		// Read in the deck from file
		try (BufferedReader br = new BufferedReader(new FileReader("deck.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				deck.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int playerHit() {
		return player.addCard(deck.remove(rand.nextInt(deck.size())));
		
	}
	
	public int playerHit(String c) {
		return player.addCard(c);
	}
	
	public int dealerHit() {
		return dealer.addCard(deck.remove(rand.nextInt(deck.size() + 1)));
	}
	
	public int dealerHit(String c) {
		return dealer.addCard(c);
	}
	
	public int playerDeal() {
		playerHit();
		return playerHit();
	}
	
	public int playerDeal(String c1, String c2) {
		return playerHit(c1) + playerHit(c2);
	}
	
	public int dealerDeal() {
		dealerHit();
		return dealerHit();
	}
	
	public int dealerDeal(String c1, String c2) {
		return dealerHit(c1) + dealerHit(c2);
	}
	
	public boolean dealerWin(CardHolder c1, CardHolder c2) {
		if (c1.getDealer()) {
			if (c1.getHandValue() == 21) {
				return true;
			}
			if (c2.getHandValue() > 21) {
				return true;
			}
			if (c1.getHandValue() > 21) {
				return false;
			}
			return (c1.getHandValue() > c2.getHandValue());
		}
		else {
			if (c2.getHandValue() == 21) {
				return true;
			}
			if (c1.getHandValue() > 21) {
				return true;
			}
			if (c2.getHandValue() > 21) {
				return false;
			}
			return (c2.getHandValue() > c1.getHandValue());
		}
		
	}
	
	public int getPlayerHandValue() { return player.getHandValue(); }
	public int getDealerHandValue() { return dealer.getHandValue(); }

}
