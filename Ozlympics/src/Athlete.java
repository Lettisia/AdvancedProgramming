import java.util.Random;

public class Athlete extends Participant {

	private double points = 0;
	
	public Athlete(String userID, String name, int age, String state) {
		super(userID, name, age, state);
		// TODO Auto-generated constructor stub
	}
	

	public int compete(String sportName) throws WrongSportException {
		int score = 0;
		
		if (sportName == "Swimming") {
				// Generate a random integer from 100 to 200
			Random randomGenerator = new Random();
			score = randomGenerator.nextInt(101)+100;
		}
		else if (sportName == "Running") {
			// Generate a random integer from 10 to 20
			Random randomGenerator = new Random();
			score = randomGenerator.nextInt(11)+10;
		}
		else if (sportName == "Cycling") {
			// Generate a random integer from 500 to 800
			Random randomGenerator = new Random();
			score = randomGenerator.nextInt(301)+500;
		}
		else {
			throw new WrongSportException("This athlete cannont compete in that sport.");
		}
		return score;
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
