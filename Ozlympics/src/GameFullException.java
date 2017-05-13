/**
 * Thrown when athlete is required to play a sport that they cannot compete in.
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
