import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a single Ozlympics Game in a particular sport with athletes and a referee.
 * may need an addAthlete() method later
 * 
 * Stores athletes and scores as a pairlist
 * 
 * @author Lettisia George
 *
 */

public class Game {
	private static final int MAX_COMPETITORS = 8;
	private static final int MIN_COMPETITORS = 4;	
	private String whichSport;
	private Official referee;
	private String gameID; 
	private boolean resultExists = false;
	private List<Pair<Integer,Athlete>> scoreAthlete;
	
	public Game() {	
		scoreAthlete = new ArrayList<Pair<Integer,Athlete>>();
	}

	public Game(String whichSport, String gameID) {
		scoreAthlete = new ArrayList<Pair<Integer,Athlete>>();
		this.whichSport = whichSport;
		this.gameID = gameID;
	}
	
	@Override
	public String toString() {
		return "Game [whichSport=" + whichSport + ", "
				+ "athletes=" + scoreAthlete.toString()
				+ ", referee=" + referee 
				+ ", gameID=" + gameID 
				+ ", resultExists=" + resultExists + "]";
	}
	
	/**
	 * @return number of athletes in this game
	 */
	public int numAthletes() {
		return scoreAthlete.size();
	}
	
	/**
	 * Method to run a game in the Ozlympics
	 * @throws NotEnoughAthletesException 
	 * @throws NoRefereeException 
	 */
	public void runGame() throws NotEnoughAthletesException, NoRefereeException {
		int count = 0;
		if (scoreAthlete.size() < MIN_COMPETITORS) {
			throw new NotEnoughAthletesException("There are not enough athletes competing in the current game. No result.");
		}
		if (referee == null) {
			throw new NoRefereeException("There is no referee for this game.");
		}
		
		Pair<Integer, Athlete> temp = null;
		ListIterator<Pair<Integer,Athlete>> it = scoreAthlete.listIterator();
		while (it.hasNext()) {
			temp = it.next();
			try {
				temp.setFirst(temp.getSecond().compete(whichSport));
				count++;
			} catch (WrongSportException e) {}			
		}
		
//		it = scoreAthlete.listIterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		if (count < MIN_COMPETITORS) {
			throw new NotEnoughAthletesException("There were not enough athletes able to compete in " + whichSport + ". No result.");
		}
		
		referee.awardPoints(scoreAthlete);
		resultExists = true;	
	}
	
	/**
	 * @return the scoreAthlete
	 */
	public List<Pair<Integer, Athlete>> getScoreAthlete() {
		return scoreAthlete;
	}

	/**
	 * @param scoreAthlete the scoreAthlete to set
	 */
	public void setScoreAthlete(List<Pair<Integer, Athlete>> scoreAthlete) {
		this.scoreAthlete = scoreAthlete;
	}
	
	/**
	 * Adds athletes to the end of the list of score athlete pairs.
	 * Score is initialised as 0.
	 *  + this.numAthletes()
	 * @param athletes
	 * @throws GameFullException
	 */
	public void addAthletes(List<Athlete> athletes) throws GameFullException {
		if (athletes.size() + this.numAthletes() > MAX_COMPETITORS) {
			throw new GameFullException();
		} else {
			ListIterator<Athlete> it = athletes.listIterator();
			while (it.hasNext()) {
				scoreAthlete.add(Pair.of(null, it.next()));
			}
		}
	}
	
	/**
	 * @return whichSport
	 */
	public String getWhichSport() {
		return whichSport;
	}
	/**
	 * @param whichSport the name of the sport to set
	 */
	public void setWhichSport(String whichSport) {
		this.whichSport = whichSport;
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
	 * @return whether a resultExists
	 */
	public boolean isResultExists() {
		return resultExists;
	}

	/**
	 * @param resultExists whether a result exists
	 */
	public void setResultExists(boolean resultExists) {
		this.resultExists = resultExists;
	}
	
//	/** 
//	 * @return Nicely formatted array of strings containing the athlete names
//	 */
//	public String [] printCompetitorNames() {
//		String [] result = new String[athletes.length];
//		for (int i = 0; i<athletes.length; i++) {
//			result[i] = Integer.toString(i+1) + ". " + athletes[i].getName();
//		}
//		return result;
//	}
//	/**
//	 *  @return Nicely formatted array of strings describing the results of the game
//	 */
//	public String [] printResult() {
//		if (resultExists) {
//			String [] temp = new String[4];
//			temp[0] = "The results for the " + whichSport + " race " + gameID + " referreed by " + referee.getName() + " are: ";
//			temp[1] = "In first place: " + athletes[0].getName();
//			temp[2] = "In second place: " + athletes[1].getName();
//			temp[3] = "and in third place: " + athletes[2].getName();
//			return temp;
//		} else {
//			String[] temp = {"No results yet for " + whichSport + " race " + gameID + ". ", "The game has not been run."};
//			return temp;
//		}
//	}
//	/**
//	 * @return the athletes in the game
//	 */
//	public Athlete [] getAthletes() {
//		return athletes;
//	}
//	/**
//	 * If there are more than 8 athletes only the first 8 will be added.
//	 * @param athletes the athletes to set 
//	 * @throws GameFullException 
//	 */
//	public void setAthletes(Athlete [] athletes) throws GameFullException {
//		if (athletes.length > MAX_COMPETITORS) {
//			//this.athletes = Arrays.copyOfRange(athletes, 0, 8);
//			throw new GameFullException();
//		} else {
//			this.athletes = athletes;
//		}
//	}
}
