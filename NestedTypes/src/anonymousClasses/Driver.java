package anonymousClasses;


public class Driver {
	public static void main(String[] args) {
		
		/* Anonymous class */
		/* based on the Tetris class */
		Tetris superTetris;
		superTetris = new Tetris() {
			/* Overridding method */
			public void dropPiece() {
				System.out.println("Super Piece Dropping");
			}
		};
		
		/* Using the object */
		superTetris.dropPiece();
		
		
		/* Anonymous class */
		/* based on the PortableDevice interface */
		PortableDevice ipod;
		ipod = new PortableDevice() {
			public int getNumberOfbatteries() {
				return 1;
			}
		};
		
        /* Using the object */
		System.out.println("Number of batteries: " + ipod.getNumberOfbatteries());
	}
}