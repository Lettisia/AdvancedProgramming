package Assign2.Algorithm;

/**
 * Thrown when an athlete is added to a game that is already full.
 * 
 * @author Lettisia George
 *
 */
public class GameFullException extends Exception {
	public GameFullException() {
	}

	public GameFullException(String arg0) {
		super(arg0);
	}
}
