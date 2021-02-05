package teamV4;

import java.util.Iterator;

public class Driver {
	public static void main(String[] args) {
		int maxSize = 10;

		Team team = new Team(maxSize);

		team.add(new Player("John", "College Park"));
		team.add(new Player("Rose", "Silver Spring"));
		team.add(new Player("Linda", "Bethesda"));

		Iterator<Player> it = team.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("***** Printing using for each *****");

		/* Now the following works because Team */
		/* implements Iterable<Player> */
		for (Player player : team) {
			System.out.println(player);
		}
	}
}