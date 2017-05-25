package Assign2.Algorithm;

/**
 * Thrown when a game is run without enough athletes.
 * 
 * @author Lettisia George
 *
 */
public class NotEnoughAthletesException extends Exception {
	public NotEnoughAthletesException() {
	}

	public NotEnoughAthletesException(String arg0) {
		super(arg0);
	}
}
