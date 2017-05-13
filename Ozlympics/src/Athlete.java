import java.util.Random;

/**
 * Represents an Athlete who can compete in Swimming, Running or Cycling events.
 * 
 * @author Lettisia George
 */
public class Athlete extends Participant {
	private double points = 0;
	
	public Athlete(String userID, String name, int age, String state) {
		super(userID, name, age, state);
	}
	
	public Athlete(String userID, String name, int age, String state, double points) {
		super(userID, name, age, state);
		this.points = points;
	}

	public int compete(String sportName) throws WrongSportException {
		int score = 0;
		if (sportName == "Swimming") {
				// Generate a random integer from 100 to 200
			Random randomGenerator = new Random();
			score = randomGenerator.nextInt(101)+100;
		} else if (sportName == "Running") {
			// Generate a random integer from 10 to 20
			Random randomGenerator = new Random();
			score = randomGenerator.nextInt(11)+10;
		} else if (sportName == "Cycling") {
			// Generate a random integer from 500 to 800
			Random randomGenerator = new Random();
			score = randomGenerator.nextInt(301)+500;
		} else {
			throw new WrongSportException("This athlete cannont compete in that sport.");
		}
		return score;
	}
	
	@Override
	public String toString() {
		String temp = "SuperAthlete, ";
		if (this instanceof Swimmer) {
			temp = "Swimmer, ";
		} else if (this instanceof Cyclist) {
			temp = "Cyclist, ";
		} else if (this instanceof Runner) {
			temp = "Runner, ";
		}
		return temp + super.toString() + ", ";
	}

	/**
	 * @return the points
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(double points) {
		this.points = points;
	}
}
