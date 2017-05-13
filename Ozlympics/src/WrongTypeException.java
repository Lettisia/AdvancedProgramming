/**
 * Thrown when trying to set an athlete as an an official or vice versa.
 * @author Lettisia George
 *
 */
public class WrongTypeException extends Exception {
	public WrongTypeException() {		
	}

	public WrongTypeException(String arg0) {
		super(arg0);
	}
}
