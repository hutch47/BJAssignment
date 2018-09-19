package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.GameManager.Royalty;

public class CardHolder {
	private List<String> cards = new ArrayList<>();
	private int handValue;
	private boolean isAce;
	CardHolder() {
		handValue = 0;
	}
	public int addCard(String c) {
		cards.add(c);
		Scanner scanner = new Scanner(c);
		while (scanner.hasNext()) {
			// Extract the value of the card into handValue
			if (scanner.hasNextInt()) {
				handValue += scanner.nextInt();
			}
			// If the card is an Ace, do special things
			else if (scanner.next() == "A") {
				isAce = true;
				handValue += 11;
			}
			else {
				handValue += Royalty.valueOf(scanner.next()).getCode();
			}
		}
		// If blackjack
		if (handValue == 21) {
			return 1;
		}
		// If under blackjack
		else if (handValue < 21) {
			return 0;
		}
		// If bust
		else if (handValue > 21) {
			if ()
			return -1;
		}
		
	}
	public String getCard(int i) {
		return cards.get(i);
	}
}
