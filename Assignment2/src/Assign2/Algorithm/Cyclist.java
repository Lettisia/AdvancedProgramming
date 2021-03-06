package Assign2.Algorithm;

import java.util.Random;

/**
 * Represents a Cyclist who can compete in Cycling events.
 * 
 * @author Lettisia George
 */
public class Cyclist extends Athlete {

	public Cyclist(String userID, String name, int age, String state) {
		super(userID, name, age, state);
	}

	public Cyclist(String userID, String name, int age, String state, double points) {
		super(userID, name, age, state, points);
	}

	@Override
	public int compete(String sportName) throws WrongSportException {
		if (sportName != "Cycling") {
			throw new WrongSportException("This athlete can only cycle. ");
		}

		// Generate a random integer from 500 to 800
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(301) + 500;
	}

	@Override
	public String getType() {
		return "cyclist";
	}
}
