
public class Game {
	private String whichSport;
	private Athlete [] athletes;
	private Official referee;
	private String gameID;
	
	
	
	public void printCompetitorNames() {
		for (int i = 0; i<athletes.length; i++) {
			System.out.println((i+1) + ". " + athletes[i].getName());
		}
	}
	
	public int numAthletes() {
		return athletes.length;
	}
	
	public void runGame() {
		
	}
	
	public String printResult() {
		String temp = "1st: " + athletes[0].getName() + "/n";
		temp = temp + ""
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
}
