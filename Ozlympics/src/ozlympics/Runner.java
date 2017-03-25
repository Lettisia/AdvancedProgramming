package ozlympics;

import java.util.Random;

public class Runner extends Athlete {
	public Runner(String userID, String name, int age, String state) {
		super(userID, name, age, state);
	}
	
	public Runner(String userID, String name, int age, String state, double points) {
		super(userID, name, age, state, points);
	}

	@Override
	public int compete(String sportName) throws WrongSportException {
		if(sportName != "Running") throw new WrongSportException("This athlete can only run. ");
		
		// Generate a random integer from 10 to 20
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(11)+10;
	}
}
