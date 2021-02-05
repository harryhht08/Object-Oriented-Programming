package teamV5;

import java.util.Iterator;

public class Team implements Iterable<Player> {
	private Player[] list;  /* good: now is private */
	private int size;	    /* good: now is private */
	
	public Team(int maxSize) {
		list = new Player[maxSize];
		size = 0;
	}
	
	public boolean add(Player player) {
		if (size < list.length) {
			list[size++] = player;
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		String answer = "";
		
		for (int i = 0; i < size; i++) { 
			answer += list[i] + "\n";
		}
		
		return answer;
	}

	/* Relying on a local class */
	public Iterator<Player> iterator() {
		 
		
		//local class 
		class TeamIterator implements Iterator<Player> {
			private int pos = 0;

			/* Notice that we no longer has to pass team */
			/* as we can access team object data directly. */
			/* Notice how the code has been simplified dramatically */

			public boolean hasNext() {
			
				return pos < size;
			}

			public Player next() {
				return list[pos++];
			}

			public void remove() {
				throw new UnsupportedOperationException("iterator remove not implemented");
			}
		}
		
		//create an instance and return it
		return new TeamIterator ();
	}
}