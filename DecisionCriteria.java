package cards;

public class DecisionCriteria {

	int currentPot;
	int currentBet;
	int numPlayersRemaining;
	Card[] communityCards;
	public int getCurrentPot() {
		return currentPot;
	}
	public void setCurrentPot(int currentPot) {
		this.currentPot = currentPot;
	}
	public int getCurrentBet() {
		return currentBet;
	}
	public void setCurrentBet(int currentBet) {
		this.currentBet = currentBet;
	}
	public int getNumPlayersRemaining() {
		return numPlayersRemaining;
	}
	public void setNumPlayersRemaining(int numPlayersRemaining) {
		this.numPlayersRemaining = numPlayersRemaining;
	}
	public Card[] getCommunityCards() {
		return communityCards;
	}
	public void setCommunityCards(Card[] communityCards) {
		this.communityCards = communityCards;
	}
}
