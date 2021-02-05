package teamV6;

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

	/* Relying on anonymous inner class */
	public Iterator<Player> iterator() {
		return new Iterator<Player>() {
			private int pos = 0;
			
			public boolean hasNext() {
				return pos < size;
			}
			
			public Player next() {
				return list[pos++];
			}
			
			public void remove() {
				throw new UnsupportedOperationException("iterator remove not implemented");
			}
			
		};
	}
}