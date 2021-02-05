package teamV2;

import java.util.Iterator;

public class TeamIterator implements Iterator<Player> {
	private Team team;
	private int pos;

	public TeamIterator(Team team) {
		this.team = team;
		pos = 0;
	}

	public boolean hasNext() {
		return pos < team.size;
	}

	public Player next() {
		return team.list[pos++];
	}

	public void remove() {
		throw new UnsupportedOperationException("iterator remove not implemented");
	}
}