/**
 * Thrown when athlete is required to play a sport that they cannot compete in.
 * @author Lettisia George
 *
 */
public class TooManyAthletesException extends Exception {
	public TooManyAthletesException() {		
	}

	public TooManyAthletesException(String arg0) {
		super(arg0);
	}
}
