/**
 * Thrown when athlete is required to play a sport that they cannot compete in.
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
