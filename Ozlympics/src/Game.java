
public class Game {
	private String whichSport;
	private Athlete [] athletes;
	private Official referee;
	private String gameID;
	private boolean resultExists = false;
	
	
	/** 
	 * @return Nicely formatted array of strings containing the athlete names
	 */
	public String [] printCompetitorNames() {
		String [] result = new String[athletes.length];
		for (int i = 0; i<athletes.length; i++) {
			result[i] = Integer.toString(i+1) + ". " + athletes[i].getName();
		}
		return result;
	}
	
	/**
	 * @return number of athletes in this game
	 */
	public int numAthletes() {
		return athletes.length;
	}
	
	public void runGame() {
		
	}
	
	/**
	 *  @return Nicely formatted array of strings describing the results of the game
	 */
	public String [] printResult() {
		String [] temp = new String[4];
		temp[0] = "The results for the " + whichSport + " race with GameId: " + gameID + " referreed by " + referee.getName() + " are: ";
		temp[1] = "In first place: " + athletes[0].getName();
		temp[2] = "In second place: " + athletes[1].getName();
		temp[3] = "and in third place: " + athletes[2].getName();
		return temp;
	}
	
	
	/**
	 * @return the whichSport
	 */
	public String getWhichSport() {
		return whichSport;
	}
	/**
	 * @param whichSport the whichSport to set
	 */
	public void setWhichSport(String whichSport) {
		this.whichSport = whichSport;
	}
	/**
	 * @return the athletes
	 */
	public Athlete [] getAthletes() {
		return athletes;
	}
	/**
	 * @param athletes the athletes to set
	 */
	public void setAthletes(Athlete [] athletes) {
		this.athletes = athletes;
	}
	/**
	 * @return the referee
	 */
	public Official getReferee() {
		return referee;
	}
	/**
	 * @param referee the referee to set
	 */
	public void setReferee(Official referee) {
		this.referee = referee;
	}
	/**
	 * @return the gameID
	 */
	public String getGameID() {
		return gameID;
	}
	/**
	 * @param gameID the gameID to set
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	/**
	 * @return the resultExists
	 */
	public boolean isResultExists() {
		return resultExists;
	}

	/**
	 * @param resultExists the resultExists to set
	 */
	public void setResultExists(boolean resultExists) {
		this.resultExists = resultExists;
	}
}
