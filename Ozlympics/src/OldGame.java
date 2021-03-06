import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a single Ozlympics Game in a particular sport with athletes and a referee.
 * may need an addAthlete() method later
 * 
 * @author Lettisia George
 *
 */
public class OldGame {
	private static final int MAX_COMPETITORS = 8;
	private static final int MIN_COMPETITORS = 4;	
	private String whichSport;
	private Athlete [] athletes;
	private Official referee;
	private String gameID; 
	private boolean resultExists = false;
	private int [] scores;
	private List<Pair<Integer,Athlete>> scoreAthlete = null;
	
	public Game() {	}

	public Game(String whichSport, String gameID) {
		this.whichSport = whichSport;
		this.gameID = gameID;
	}
	
	@Override
	public String toString() {
		return "Game [whichSport=" + whichSport + ", athletes=" + Arrays.toString(athletes) 
				+ ", referee=" + referee + ", gameID=" + gameID + ", resultExists=" + resultExists + "]";
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
	 * @return the winning athlete or null if game not successful
	 * @throws NotEnoughAthletesException 
	 * @throws NoRefereeException 
	 */
	public Athlete runGame() throws NotEnoughAthletesException, NoRefereeException {
		int count = 0;
		scores = new int [athletes.length];	
		List <Pair<Integer,Athlete>> pairList = new ArrayList<Pair<Integer,Athlete>>();
		
		// Check number of competitors who can compete (4-8)
		if (athletes.length < MIN_COMPETITORS) {
			//System.out.println("There are not enough athletes competing in the current game. No result.");
			throw new NotEnoughAthletesException("There are not enough athletes competing in the current game. No result.");
		}
		
		if (referee == null) {
			throw new NoRefereeException();
		}
		
		// Get points for each competitor
		for (int i = 0; i<athletes.length; i++) {
			try {
				scores[i] = athletes[i].compete(whichSport);
				Pair<Integer,Athlete> temp = Pair.of(scores[i], athletes[i]);
				pairList.add(temp);
				count++;
			} catch (WrongSportException e) {	}
		}
		if (count < MIN_COMPETITORS) {
			//System.out.println("There were not enough athletes able to compete in " + whichSport + ". No result.");
			//return null;
			throw new NotEnoughAthletesException("There were not enough athletes able to compete in " + whichSport + ". No result.");
		}
//		for (int i : scores) {
//			System.out.println(i);
//		}
		
//		ListIterator<Pair<Integer,Athlete>> it = pairList.listIterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		// Ask official to rank athletes and generate scores
		referee.scoreGame(pairList);
//		it = pairList.listIterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		
		referee.scoreGame(athletes, scores);
//		for( int i : scores) {
//			System.out.println(i);
//		}
//		Arrays.sort(scores);
//		referee.awardPoints(athletes);
		referee.awardPoints(pairList);
		resultExists = true;	
		return athletes[0];
	}
	
	/**
	 *  @return Nicely formatted array of strings describing the results of the game
	 */
	public String [] printResult() {
		if (resultExists) {
			String [] temp = new String[4];
			temp[0] = "The results for the " + whichSport + " race " + gameID + " referreed by " + referee.getName() + " are: ";
			temp[1] = "In first place: " + athletes[0].getName();
			temp[2] = "In second place: " + athletes[1].getName();
			temp[3] = "and in third place: " + athletes[2].getName();
			return temp;
		} else {
			String[] temp = {"No results yet for " + whichSport + " race " + gameID + ". ", "The game has not been run."};
			return temp;
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
	 * @return the athletes in the game
	 */
	public Athlete [] getAthletes() {
		return athletes;
	}
	/**
	 * If there are more than 8 athletes only the first 8 will be added.
	 * @param athletes the athletes to set 
	 * @throws GameFullException 
	 */
	public void setAthletes(Athlete [] athletes) throws GameFullException {
		if (athletes.length > MAX_COMPETITORS) {
			//this.athletes = Arrays.copyOfRange(athletes, 0, 8);
			throw new GameFullException();
		} else {
			this.athletes = athletes;
		}
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

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}
}
