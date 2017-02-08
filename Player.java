package cards;

import static cards.HandName.*;

public class Player {

	


	private String name;
	
	private Card c1;
	
	private Card c2;
	
	private int stack;
	
	int position, action, pot;
	
	public Player(String name, int initialStack) {
		this.name = name;
		this.stack = initialStack;
	}
	
/*	
 	public void dealCards(Card c1, Card c2) {
	
		this.c1 = c1;
		this.c2 = c2;
	}
*/
	public String name() {
		return name;
	}
	
	public void setCard1(Card c1) {
		this.c1 = c1;
	}
	
	public void setCard2(Card c2) {
		this.c2 = c2;
	}
	
	public Card getCard1() {
		return c1;
	}
	
	public Card getCard2() {
		return c2;
	}
	
	public int getStack()
	{
		return stack;
	}
	
	public void debit(int amount) {
		credit(-amount);
	}
	
	public void credit(int amount) {
		stack += amount;
	}
	
	public int action(DecisionCriteria criteria) {
		Hand hand = new Hand(c1, c2);
		
		HandName handName = CardsEvaluator.getHand(hand);

		if(handName == rags) {
			return 0;
		}	
		else if (handName == aces) {
			return stack;
		}
		else {
			return criteria.getCurrentBet();
		}
		
	}
	
	public void evaluate() {
		
		Hand hand = new Hand(c1, c2);
		
		HandName handName = CardsEvaluator.getHand(hand);

		if(handName == aces || handName == kings) {
			System.out.println("Raise");
		}
	}
	
	
	public boolean stillPlaying(){
		return true; //Decision d.choice
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/*
	public int decide(int position, int action, int pot){
		Decision d = new Decision(position, action, pot, stack);
		if (d.choice > 0){
			return d.Raise();
			break;
		}
		if (d.choice = 0){
			return d.Call();
			break;
		}
		return d.Fold();
		}
		*/
			
		
	}
	

	
