
public abstract class Athlete extends Participant {


	protected boolean canSwim = false;
	protected boolean canRun = false;
	protected boolean canCycle = false;
	protected double points = 0;
	
	public Athlete(String userID, String name, int age, String state) {
		super(userID, name, age, state);
		// TODO Auto-generated constructor stub
	}
	

	public abstract int compete(String sportName) throws WrongSportException;
	
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
