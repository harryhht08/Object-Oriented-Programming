package anonymousClasses;

public class Tetris {
	private int score;
	
	public int getScore() { return score; }
	public void setScore(int score) { this.score = score; }
	public void rotatePiece() {
		System.out.println("Rotating");
	}
	public void dropPiece() {
		System.out.println("Dropping");
	}
}
