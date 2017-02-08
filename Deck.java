package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

	private final List<Card> deckOfCards;
	
	public Deck() {
		deckOfCards = new ArrayList<Card>(52);
		for(Card c : Card.values()) {
			deckOfCards.add(c);
		}
		
		for(Card card : deckOfCards) {
			//System.out.println(card);
		}	
	}
	
	public void shuffle() {
		Collections.shuffle(deckOfCards);
	}
	
	public Card dealCard() {
		
		if(!deckOfCards.isEmpty()) {
			return deckOfCards.remove(0);
		}
		return null;
	}
}
