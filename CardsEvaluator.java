package cards;

import static cards.HandName.*;
import static cards.Rank.*;

public class CardsEvaluator {

	
	/**
	 * Call this method to get the hand name
	 * @param h
	 * @return
	 */
	public static HandName getHand(Hand h) {
		
		Card [] cards = h.get();
		
		Card c1 = cards[0];
		Card c2 = cards[1];
		
		if(arePockets(c1,c2)) {
			
			HandName pockets = null;
			
			switch(c1.rank()) {
			
			case ACE: 	pockets = aces;
						break;
			case KING: 	pockets = kings;
						break;
			case QUEEN: pockets = queens;
						break;
			case JACK: 	pockets = jacks;
						break;
			case TEN: 	pockets = tens;
						break;
			case NINE: 	pockets = nines;
						break;
			case EIGHT: pockets = eights;
						break;
			case SEVEN: pockets = sevens;
						break;
			case SIX: 	pockets = sixes;
						break;
			case FIVE:	pockets = fives;
						break;
			case FOUR: 	pockets = fours;
						break;
			case THREE: pockets = threes;
						break;
			case TWO: 	pockets = deuces;
						break;
			}
			return pockets;			
		}		
		
		// Identify which is the high/low ranked cards, in order to pass for evaluation.
		Card high = (c1.rank().getValue() > c2.rank().getValue())
				? c1
				: c2;
		
		Card low = (c1.rank().getValue() < c2.rank().getValue())
				? c1
				: c2;
							
		if(high.rank() == ACE) {
			if(low.rank() == KING)
				return (areSuited(high,low)) ? suited_AK : offsuit_AK;
			if(low.rank() == QUEEN)
				return (areSuited(high,low)) ? suited_AQ : offsuit_AQ;
			if(low.rank() == JACK)
				return (areSuited(high,low)) ? suited_AJ : offsuit_AJ;
			if(low.rank() == TEN)
				return (areSuited(high,low)) ? suited_AT : offsuit_AT;
			if(low.rank().getValue() > 5)
				return (areSuited(high,low)) ? suited_A6_9 : offsuit_A6_9;
			if(low.rank().getValue() <= 5)
				return (areSuited(high,low)) ? suited_A2_5 : offsuit_A2_5;
			else throw new RuntimeException("Invalid rank - THIS SHOULD NOT HAPPEN");
		}
		
		if(high.rank() == KING) {
			if(low.rank() == QUEEN)
				return (areSuited(high,low)) ? suited_KQ : offsuit_KQ;
			if(low.rank() == JACK)
				return (areSuited(high,low)) ? suited_KJ : offsuit_KJ;
			if(low.rank() == TEN)
				return (areSuited(high,low)) ? suited_KT : offsuit_KT;
			if(low.rank() == NINE)
				return (areSuited(high,low)) ? suited_K9 : offsuit_K9;
			if(low.rank().getValue() < 9)
				return (areSuited(high,low)) ? suited_Kx : rags; //offsuit_Kx;
			else throw new RuntimeException("Invalid rank - THIS SHOULD NOT HAPPEN");
		}
		
		if(high.rank() == QUEEN) {
			if(low.rank() == JACK)
				return (areSuited(high,low)) ? suited_QJ : offsuit_QJ;
			if(low.rank() == TEN)
				return (areSuited(high,low)) ? suited_QT : offsuit_QT;
			if(low.rank() == NINE)
				return (areSuited(high,low)) ? suited_Q9 : offsuit_Q9;
			if(low.rank() == EIGHT)
				return (areSuited(high,low)) ? suited_Q8 : offsuit_Q8;
			if(low.rank().getValue() < 9)
				return (areSuited(high,low)) ? suited_Qx : rags; //offsuit_Qx;
			else throw new RuntimeException("Invalid rank - THIS SHOULD NOT HAPPEN");
		}
		
		if(high.rank() == JACK) {
			if(low.rank() == TEN)
				return (areSuited(high,low)) ? suited_JT : offsuit_JT;
			if(low.rank() == NINE)
				return (areSuited(high,low)) ? suited_J9 : offsuit_J9;
			if(low.rank() == EIGHT)
				return (areSuited(high,low)) ? suited_J8 : offsuit_J8;
			if(low.rank() == SEVEN)
				return (areSuited(high,low)) ? suited_J7 : offsuit_J7;
			if(low.rank().getValue() < 9)
				return (areSuited(high,low)) ? suited_Jx : rags; //offsuit_Jx;
			else throw new RuntimeException("Invalid rank - THIS SHOULD NOT HAPPEN");
		}
		
		
		if(high.rank() == TEN) {
			if(low.rank() == TEN)
				return (areSuited(high,low)) ? suited_T9 : offsuit_T9;
			if(low.rank() == NINE)
				return (areSuited(high,low)) ? suited_T8 : offsuit_T8;
		}
		
		
		if(high.rank().getValue() - low.rank().getValue() == 1) {
			return (areSuited(high,low)) ? suited_connectors : connectors;
		}
		
		
		if(high.rank().getValue() - low.rank().getValue() == 2) {
			return (areSuited(high,low)) ? suited_one_gap : connected_one_gap;
		}

		return rags;		
	}
	
	private static boolean areSuited(Card c1, Card c2) {
		return c1.suit() == c2.suit();
	}
	
	private static boolean arePockets(Card c1, Card c2) {
		return c1.rank() == c2.rank();
	}
}
