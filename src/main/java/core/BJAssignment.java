package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJAssignment {
	
	public static void main(String[] args) throws IOException {
		boolean playing = true;
		boolean reading = false;
		boolean gameWon;
		boolean dealerWinning;
		GameManager GM;
		
		// Main loop
		while (true) {
			dealerWinning = false;
			gameWon = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Hello! Welcome to Blackjack!");
			System.out.println("Would you like to read from file? Or would you like to play from a full deck?");
			
			// Keep asking until valid input
			while (true) {
			System.out.print("Enter (r) to read, or (p) to play, or (q) to quit program: ");
			String choice = br.readLine();
				if (choice.equals("p")) {
					playing = true;
					break;
				}
				else if (choice.equals("r")) {
					playing = false;
					break;
				}
				else if (choice.equals("q")) {
					return;
				}
				else {
					System.out.println("Option invalid!");
				}
			}
			
			//Game loop (playing)
			if (playing) {
				
				GM = new GameManager();
				// Deal the cards and display
				GM.playerDeal();
				GM.dealerDeal();
				System.out.print("Your cards are: ");
				for (int i = 0; i < GM.player.getNumCards(); i++) {
					System.out.print(GM.player.getCard(i) + " ");
				}
				System.out.println();
				System.out.print("The dealer has these cards shown: XX ");
				for (int i = 1; i < GM.dealer.getNumCards(); i++) {
					System.out.print(GM.dealer.getCard(i) + " ");
				}
				System.out.println();
				if (GM.dealerBJ()) {
					System.out.println("Dealer Wins!");
					playing = false;
					gameWon = true;
					break;
				}
				else if (!GM.dealerBJ() && GM.playerBJ()) {
					System.out.println("You win!");
					playing = false;
					gameWon = true;
					break;
				}
				
				// If no initial win, continue game
				while (!gameWon) {
					dealerWinning = GM.dealerWin();
					while (true) {
						System.out.print("Would you like to (h)it? Or (s)tand: ");
						String choice = br.readLine();
						
						// If hitting
						if (choice.equals("h")) {
							GM.playerHit();
							if (GM.playerBust()) {
								System.out.println("You Bust!");
								dealerWinning = true;
								gameWon = true;
								break;
							}
							System.out.println();
							System.out.print("Your cards are: ");
							for (int i = 0; i < GM.player.getNumCards(); i++) {
								System.out.print(GM.player.getCard(i) + " ");
							}
							System.out.println();
							System.out.print("The dealer has these cards shown: XX ");
							for (int i = 1; i < GM.dealer.getNumCards(); i++) {
								System.out.print(GM.dealer.getCard(i) + " ");
							}
							System.out.println();
						}
						
						// If staying
						else if (choice.equals("s")) {
							System.out.print("The dealer has these cards shown: XX ");
							for (int i = 1; i < GM.dealer.getNumCards(); i++) {
								System.out.print(GM.dealer.getCard(i) + " ");
							}
							System.out.println();
							while (GM.dealerHittable()) {
								GM.dealerHit();
								System.out.print("The dealer has these cards shown: XX ");
								for (int i = 1; i < GM.dealer.getNumCards(); i++) {
									System.out.print(GM.dealer.getCard(i) + " ");
								}
								System.out.println();
							}
							if (GM.dealerBust()) {
								System.out.println("The dealer busts");
							}
							else
								System.out.println("The dealer stands");
							System.out.print("The dealer has these cards shown: ");
							for (int i = 0; i < GM.dealer.getNumCards(); i++) {
								System.out.print(GM.dealer.getCard(i) + " ");
							}
							System.out.println();
							if (GM.dealerWin()) {
								dealerWinning = true;
							}
							break;
							
						}
					}
					gameWon = true;
					if (dealerWinning) {
						System.out.println("Dealer wins!");
						break;
					}
					else {
						System.out.println("You win!");
						break;
					}
				}
			}
			
			//Game loop (reading)
			else {
				
			}
		}
		
	}
}
