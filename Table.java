package cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Table {

	private Player[] players;
	
	private Deck deck;
	
	private final int BLIND_AMOUNT = 2;
	
	private Map<Player,Boolean> activePlayersActionStatus;
	
	private int DEALER = 0;
		
	private int SMALL_BLIND = 1;
	
	private int BIG_BLIND = 2;
	
	private int UTG = 3;
	
	private int numGames = 1;
	
	private int action = 0;
	
	private int pot = 0;
	
	public Table(Player[] players) {
		
		this.players = players;
	}
	
	//private Card [] cardsInPlay = null;
	
	public void newGame() {
	
		System.out.println("GAME #" + numGames);
		System.out.println(players.length + " players");
		System.out.println("===========");
		
		activePlayersActionStatus = new HashMap<Player,Boolean>();
		for(Player p : players) {
			activePlayersActionStatus.put(p, false);
		}

		deck = new Deck();
		deck.shuffle();
		
		dealing(); 
		preflopBetting();/*
		flop();
		flopBetting();
		turn();
		turnBetting();
		river();
		riverBetting();
		moveButton();*/
	}
	
	private void dealing(){
		
		
		System.out.println("====================");
		System.out.println("ROUND: " + Rounds.DEALING);
		System.out.println("====================");
		
		// DEAL CARDS -- update this to start with "under the gun"
		for(int j = SMALL_BLIND; j < players.length; j++) {
			//players[j].dealCards(deck.dealCard(), deck.dealCard());
			players[j].setCard1(deck.dealCard());
			players[j].setCard2(deck.dealCard());
		}
		
		for(int j = 0; j < SMALL_BLIND; j++) {
			//players[j].dealCards(deck.dealCard(), deck.dealCard());
			players[j].setCard1(deck.dealCard());
			players[j].setCard2(deck.dealCard());
		}
		
		System.out.println("List of cards preflop");
		System.out.println("---------------------");
		
		for(int i = 0; i < players.length; i++) {
			
			Card c1 = players[i].getCard1();
			Card c2 = players[i].getCard2();
			System.out.println(players[i].name() + showPosition(i));
			System.out.println(c1 + ", " + c2 + " ==> [" + CardsEvaluator.getHand(new Hand(c1,c2)) + "]");
			System.out.println();
			

		}
	}
		
		
	private void preflopBetting(){
		
		System.out.println("====================");
		System.out.println("ROUND: " + Rounds.PRE_FLOP_BETTING);
		System.out.println("====================");

		postBlinds();
				
		// PRE-FLOP DECISION-MAKING -- update this to start with "UTG"
		
		int folders = 0;
		int numPlayersAtFlop = activePlayersActionStatus.size();
		
		for(int j = UTG; j < numPlayersAtFlop; j++) {
			if(activePlayersActionStatus.get(players[j])) {
				break;
			}
			doPlayerAction(players[j], j);			
		}
		// wrap around the other side of the table
		for(int j = 0; j < UTG; j++) {
			
			if(activePlayersActionStatus.get(players[j])) {
				break;
			}
			doPlayerAction(players[j], j);			
		}
	}
	
	private int countNumPlayersLeftToAct() {
		int count = 0;
		for(Boolean b : activePlayersActionStatus.values()) {
			count = (!b) ? count + 1 : count;
		}
		return count;
	}
	
	private void doPlayerAction(Player player, int pos) {
		
		System.out.println(countNumPlayersLeftToAct() + " players left >>");
		System.out.println(">> On " + player.name() + showPosition(pos) + " [$" + player.getStack() + "]:");
		
		DecisionCriteria criteria = new DecisionCriteria();
		criteria.setCurrentBet(action);
		criteria.setCurrentPot(pot);
		criteria.setNumPlayersRemaining(countNumPlayersLeftToAct());
		
		int bet = player.action(criteria);
			
		if (bet > player.getStack()) {
			throw new RuntimeException("ERROR: Cannot bet more than player's stack.  This shouldn't happen");
		}
		// FOLD: If the bet cannot meet the current action, for which the player can afford
		else if(bet < action && action < player.getStack()) {
			System.out.println(player.name() + " folds");
			activePlayersActionStatus.remove(player);
		}
		// CALL: Current bet is met or all-in for less
		else if(bet == action || (bet < action && bet == player.getStack())) {
			System.out.println(player.name() + " calls");
			activePlayersActionStatus.put(player,  true); // Yes, I've completed my action and still in the hand.
			
			if(pos == SMALL_BLIND) {
				player.credit(BLIND_AMOUNT/2); // give back the small blind
				player.debit(BLIND_AMOUNT);  // pay the full blind;
			}
			else if (pos != BIG_BLIND) {
				player.debit(bet);
			}
			System.out.println(player.name() + " has $" + player.getStack() + " remaining");
		}
		// RAISE:
		else {
			for(Player p : activePlayersActionStatus.keySet()) {
				activePlayersActionStatus.put(p, false);  // every player gets another chance to act if already did
				
			}
			System.out.println(player.name() + " raises to " + bet);
			activePlayersActionStatus.put(player, true);
			player.debit(bet);
		}
		System.out.println();
	}
		
	private String showPosition(int index) {
		if(index == DEALER) return " (DEALER)";
		if(index == SMALL_BLIND) return " (SB)";
		if(index == BIG_BLIND) return " (BB)";
		if(index == UTG) return " (UTG)";
		return "";
	}
	
	
	
	private void moveButton() {
		DEALER = (DEALER == players.length-1) ? 0 : DEALER+1;
		SMALL_BLIND = (SMALL_BLIND == players.length-1) ? 0 : SMALL_BLIND+1;
		BIG_BLIND = (BIG_BLIND == players.length-1) ? 0 : BIG_BLIND+1;
		UTG = (UTG == players.length-1) ? 0 : UTG+1;

		System.out.println("Button is on " + players[DEALER].name());
		System.out.println("SB is on " + players[SMALL_BLIND].name());
		System.out.println("BB is on " + players[BIG_BLIND].name());
		System.out.println("UTG is on " + players[UTG].name());
	}	

	
	private void postBlinds(){
		
		int smallBlindAmt = BLIND_AMOUNT/2;
		int bigBlindAmt = BLIND_AMOUNT;
		
		action = bigBlindAmt;
		pot += smallBlindAmt + bigBlindAmt;
		
		players[SMALL_BLIND].debit(smallBlindAmt);
		players[BIG_BLIND].debit(bigBlindAmt);
		System.out.println("Action is at: $" + action);
		System.out.println("The pot is at: $" + pot);
		System.out.println("--------------------");
	}

}
