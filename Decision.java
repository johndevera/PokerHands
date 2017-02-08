package cards;

import static cards.HandName.aces;
import static cards.HandName.kings;

public class Decision {

	// Think about how to write this method
	
	int position, action, pot, stack;
	
	private Decision(int position, int action, int pot, int stack){
		
		this.position = position;
		this.action = action;
		this.pot = pot;
		this.stack = stack;		
		//evaluate();
		
	}
	

/*
	public void evaluate() {
		
		Hand hand = new Hand(c1, c2);
		
		HandName handName = CardsEvaluator.getHand(hand);

		if(handName == aces || handName == kings) {
			System.out.println("Raise");
		}
	}
*/
	public int choice(){
		return 0;
	}
	
	public int Raise(){
		return 0;
	}
	public int Call(){
		return 0;
	}
	public int Fold(){
		return 0;
	}
	
}