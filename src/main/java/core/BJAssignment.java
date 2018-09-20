package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
					System.out.println("Dealer Wins! Blackjack!");
					playing = false;
					gameWon = true;
					break;
				}
				else if (!GM.dealerBJ() && GM.playerBJ()) {
					System.out.println("You win! Blackjack!");
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
				gameWon = false;
				List<String> inst = new ArrayList<>();
				GM = new GameManager();
				
				// Get file name and store each instruction
				while (true) {
				System.out.print("Please enter the file name: ");
				String fName = br.readLine();
				try (Scanner sc = new Scanner(new File("src/main/resources/"+fName))) {
					while (sc.hasNext()) {
						inst.add(sc.next());
					}
					break;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("File not found!");
				}
				}
				
				// Run the game
				GM.playerDeal(inst.remove(0), inst.remove(0));
				GM.dealerDeal(inst.remove(0), inst.remove(0));
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
					System.out.println("Dealer Wins! Blackjack!");
					playing = false;
					gameWon = true;
					break;
				}
				else if (!GM.dealerBJ() && GM.playerBJ()) {
					System.out.println("You win! BlackJack!");
					playing = false;
					gameWon = true;
					break;
				}
				
				// If no initial win, continue game
				while (!gameWon) {
					dealerWinning = GM.dealerWin();
					while (inst.size() > 0) {
						
						
						// If hitting
						if (inst.get(0).equals("H")) {
							inst.remove(0);
							GM.playerHit(inst.remove(0));
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
							if (inst.size() == 0)
								break;
						}
						
						// If staying
						else if (inst.get(0).equals("S")) {
							inst.remove(0);
							System.out.println("The player is staying...");
							System.out.print("The dealer has these cards shown: XX ");
							for (int i = 1; i < GM.dealer.getNumCards(); i++) {
								System.out.print(GM.dealer.getCard(i) + " ");
							}
							System.out.println();
							while (GM.dealerHittable() && inst.size() > 0) {
								System.out.println("The dealer is hitting...");
								GM.dealerHit(inst.remove(0));
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
		}
		
	}
}
