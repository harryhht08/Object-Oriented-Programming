package teamV5;

public class Player {
	private String name;
	private String address;
	
	public Player(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public String toString() {
		return "Player [name=" + name + ", address=" + address + "]";
	}
}