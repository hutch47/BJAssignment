package core;

import java.util.ArrayList;
import java.util.List;
import core.Royal;

public class CardHolder {
	private List<String> cards = new ArrayList<>();
	private int handValue;
	private int aces;
	private boolean isDealer;
	CardHolder() {
		handValue = 0;
		aces = 0;
		isDealer = false;
	}
	
	CardHolder(boolean isDealer) {
		this.isDealer = isDealer;
	}
	
	public int addCard(String c) {
		cards.add(c);
		if (c.contains("A")) {
			aces++;
			handValue += 11;
		}
		else if (c.replaceAll("[^0-9]", "").isEmpty()) {
			handValue += Royal.valueOf(c.substring(1,2)).getValue();
		}
		else
			handValue += Integer.parseInt(c.replaceAll("[^0-9]", ""));
		// If bust, check for aces
		if (handValue > 21) {
			if (aces > 0) {
				handValue -= aces*10;
				aces = 0;
			}
			else return -1;
		}
		// If blackjack
		if (handValue == 21) {
			return 1;
		}
		// If under blackjack
		else {
			return 0;
		}
		
	}
	public String getCard(int i) {
		return cards.get(i);
	}
	
	public boolean getDealer() { return isDealer; }
	
	public int getHandValue() { return handValue; }
	
	public int getNumCards() { return cards.size(); }
	
	public boolean hasAces() { return aces > 0; }
}
