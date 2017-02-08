package cards;

public class MainProgram {

	public static void main (String [] args) {
		
		Player p1 = new Player("Alan", 200);
		Player p2 = new Player("Ben", 200);
		Player p3 = new Player("Charlie", 200);
		Player p4 = new Player("Dave", 200);
		Player p5 = new Player("Eric", 200);
		Player p6 = new Player("Frank", 200);
		Player p7 = new Player("George", 200);
		Player p8 = new Player("Harry", 200);
		Player p9 = new Player("Ivan", 200);
		
		Player [] players = {p1,p2,p3,p4,p5,p6,p7,p8,p9};
		Table table = new Table(players);
		table.newGame();
	}
}
