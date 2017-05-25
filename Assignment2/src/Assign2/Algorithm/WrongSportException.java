package Assign2.Algorithm;

/**
 * Thrown when athlete is required to play a sport that they cannot compete in.
 * 
 * @author Lettisia George
 *
 */
public class WrongSportException extends Exception {
	public WrongSportException() {
	}

	public WrongSportException(String arg0) {
		super(arg0);
	}
}
