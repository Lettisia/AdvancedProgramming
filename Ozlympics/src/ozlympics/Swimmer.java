package ozlympics;

import java.util.Random;

/**
 * Represents a Swimmer who can compete in Swimming events.
 * 
 * @author Lettisia George
 */
public class Swimmer extends Athlete {
		
	public Swimmer(String userID, String name, int age, String state) {
		super(userID, name, age, state);
	}
	
	public Swimmer(String userID, String name, int age, String state, double points) {
		super(userID, name, age, state, points);
	}

	@Override
	public int compete(String sportName) throws WrongSportException {
		if(sportName != "Swimming") {
			throw new WrongSportException("This athlete can only swim. ");
		}
		
		// Generate a random integer from 100 to 200
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(101)+100;
	}
}
