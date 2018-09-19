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
		dealer = new CardHolder();
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
		return player.addCard(c);
	}
	
	public int playerDeal() {
		return playerHit() + playerHit();
	}
	
	public int playerDeal(String c1, String c2) {
		return playerHit(c1) + playerHit(c2);
	}
	
	public int dealerDeal() {
		return dealerHit() + dealerHit();
	}
	
	public int dealerDeal(String c1, String c2) {
		return dealerHit(c1) + dealerHit(c2);
	}
	
	public int getPlayerHandValue() { return player.getHandValue(); }
}
