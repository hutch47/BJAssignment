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
		player.addCard(deck.remove(rand.nextInt(deck.size())));
		
	}
	
	public int playerHit(String c) {
		
	}
	
	public int dealerHit() {
		
	}
	
	public int dealerHit(String c) {
		
	}
	
	public int playerDeal() {
		
	}
	
	public int playerDeal(String c1, String c2) {
		
	}
	
	public int dealerDeal() {
		
	}
	
	public int dealerDeal(String c1, String c2) {
		
	}
}
