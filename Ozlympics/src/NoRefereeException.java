/**
 * Thrown when a game is started with no referee.
 * @author Lettisia George
 *
 */
public class NoRefereeException extends Exception {
	public NoRefereeException() {		
	}

	public NoRefereeException(String arg0) {
		super(arg0);
	}
}
