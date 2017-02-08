package cards;

/**
 * Key points:
 * 1) Card member variables (first, second) should never change after construction (i.e., the caller
 * shouldnt be able to change the underlying hand --> hence use final)
 * 2) Check for valid cards only
 * @author bdeve_000
 *
 */
public class Hand {

	private final Card first;
	private final Card second;
	
	public Hand(Card first, Card second) {
		if(first == null || second == null) {
			throw new RuntimeException("Cannot create invalid cards");
		}
		this.first = first;
		this.second = second;
	}
	
	public Card[] get() {
		return new Card [] {first, second};
	}
}
