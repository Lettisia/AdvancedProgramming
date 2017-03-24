package ozlympics;

import java.util.Arrays;

public class Game {
	private String whichSport;
	private Athlete [] athletes;
	private Official referee;
	private String gameID; 
	private boolean resultExists = false;
	private final int maxCompetitors = 8;
	private final int minCompetitors = 4;	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [whichSport=" + whichSport + ", athletes=" + Arrays.toString(athletes) + ", referee=" + referee
				+ ", gameID=" + gameID + ", resultExists=" + resultExists + "]";
	}

	public Game() {
	}

	public Game(String whichSport, String gameID) {
		this.whichSport = whichSport;
		this.gameID = gameID;
	}

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
	
	/**
	 * Method to run a game in the Ozlympics
	 * @return true if game successfully run
	 */
	public boolean runGame() {
		// Check number of competitors who can compete (4-8)
		if (athletes.length < minCompetitors) {
			System.out.println("There are not enough athletes competing in the current game. No result.");
			return false;
		}
		
		
		// Get points for each competitor
		int count = 0;
		int [] scores = new int [athletes.length];
		for(int i = 0; i<athletes.length; i++) {
			try {
				scores[i] = athletes[i].compete(whichSport);
				count++;
			}
			catch (WrongSportException e) {	}
		}
		
		if (count < minCompetitors) {
			System.out.println("There were not enough athletes able to compete in " + whichSport + ". No result.");
			return false;
		}
				
		
		// Ask official to rank athletes and generate scores
		athletes = referee.scoreGame(athletes, scores);
		referee.awardPoints(athletes);
		resultExists = true;	
		return true;
	}
	
	/**
	 *  @return Nicely formatted array of strings describing the results of the game
	 */
	public String [] printResult() {
		if (resultExists) {
			String [] temp = new String[4];
			temp[0] = "The results for the " + whichSport + " race with GameId: " + gameID + " referreed by " + referee.getName() + " are: ";
			temp[1] = "In first place: " + athletes[0].getName();
			temp[2] = "In second place: " + athletes[1].getName();
			temp[3] = "and in third place: " + athletes[2].getName();
			return temp;
		}
		else {
			String[] temp = {"No results yet", "The game has not been run."};
			return temp;
		}
	}
	
	
	/*
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
