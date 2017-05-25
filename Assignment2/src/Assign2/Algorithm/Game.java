package Assign2.Algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a single Ozlympics Game in a particular sport with athletes and a
 * referee. may need an addAthlete() method later
 * 
 * Stores athletes and scores as a list of pairs
 * 
 * @author Lettisia George
 *
 */

public class Game {
	private static final int MAX_COMPETITORS = 8;
	private static final int MIN_COMPETITORS = 5;
	private String whichSport;
	private Official referee;
	private String gameID;
	private boolean resultExists = false;
	private List<Pair<Integer, Athlete>> scoreAthlete;

	public Game() {
		scoreAthlete = new ArrayList<Pair<Integer, Athlete>>();
	}

	public Game(String whichSport, String gameID) {
		scoreAthlete = new ArrayList<Pair<Integer, Athlete>>();
		this.whichSport = whichSport;
		this.gameID = gameID;
	}

	@Override
	public String toString() {
		return "Game [whichSport=" + whichSport + ", " + "athletes=" + scoreAthlete.toString() + ", referee=" + referee
				+ ", gameID=" + gameID + ", resultExists=" + resultExists + "]";
	}

	/**
	 * @return number of athletes in this game
	 */
	public int numAthletes() {
		return scoreAthlete.size();
	}

	/**
	 * Method to run a game in the Ozlympics
	 * 
	 * @throws NotEnoughAthletesException
	 * @throws NoRefereeException
	 */
	public void runGame() throws NotEnoughAthletesException, NoRefereeException {
		int count = 0;

		if (scoreAthlete.size() < MIN_COMPETITORS) {
			throw new NotEnoughAthletesException(
					"There are not enough athletes competing in the current game. No result.");
		}

		if (referee == null) {
			throw new NoRefereeException("There is no referee for this game.");
		}

		Pair<Integer, Athlete> temp = null;
		ListIterator<Pair<Integer, Athlete>> it = scoreAthlete.listIterator();

		while (it.hasNext()) {
			temp = it.next();
			try {
				temp.setFirst(temp.getSecond().compete(whichSport));
				count++;
			} catch (WrongSportException e) {
			}
		}

		if (count < MIN_COMPETITORS) {
			throw new NotEnoughAthletesException(
					"There were not enough athletes able to compete in " + whichSport + ". No result.");
		}

		referee.awardPoints(scoreAthlete);
		resultExists = true;
	}

	public String printCompetitorResults() {
		String[] ords = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th" };
		String names = "";
		int i = 0;

		for (Pair<Integer, Athlete> athlete : scoreAthlete) {
			names = names + ords[i] + " (" + athlete.getFirst() + "s) " + athlete.getSecond().getName() + "\n";
			i++;
		}
		return names;
	}

	public String printResult() {
		if (resultExists) {
			String temp = gameID + " - " + whichSport + " Event \n";
			temp = temp + "Referee: " + referee.getName() + "\n";
			temp = temp + "1st: " + scoreAthlete.get(0).getSecond().getName() + "\n";
			temp = temp + "2nd: " + scoreAthlete.get(1).getSecond().getName() + "\n";
			temp = temp + "3rd: " + scoreAthlete.get(2).getSecond().getName() + "\n";
			return temp;
		} else {
			String temp = "No results yet for " + whichSport + " event. The game has not been run.";
			return temp;
		}
	}

	/**
	 * Adds athletes to the end of the list of score athlete pairs. Score is
	 * initialised as 0.
	 * 
	 * @param athletes
	 *            List of athletes to add
	 * @throws GameFullException
	 */
	public void addAthletes(List<Athlete> athletes) throws GameFullException {
		if (athletes.size() + this.numAthletes() > MAX_COMPETITORS) {
			throw new GameFullException();
		} else {
			ListIterator<Athlete> it = athletes.listIterator();
			while (it.hasNext()) {
				scoreAthlete.add(Pair.of(0, it.next()));
			}
		}
	}

	public boolean isAthlete(String userID) {
		Iterator<Pair<Integer, Athlete>> iterator = scoreAthlete.iterator();
		while (iterator.hasNext()) {
			if (userID.equals(iterator.next().getSecond().getUserID())) {
				return true;
			}
		}
		return false;
	}

	public Athlete findAthlete(String userID) {
		Iterator<Pair<Integer, Athlete>> iterator = scoreAthlete.iterator();
		while (iterator.hasNext()) {
			Pair<Integer, Athlete> next = iterator.next();
			if (userID.equals(next.getSecond().getUserID())) {
				return next.getSecond();
			}
		}
		return null;
	}

	public String printGameName() {
		return gameID;// + " (" + whichSport + " Event)";
	}

	public List<Pair<Integer, Athlete>> getScoreAthlete() {
		return scoreAthlete;
	}

	public void setScoreAthlete(List<Pair<Integer, Athlete>> scoreAthlete) {
		this.scoreAthlete = scoreAthlete;
	}

	public String getWhichSport() {
		return whichSport;
	}

	public void setWhichSport(String whichSport) {
		this.whichSport = whichSport;
	}

	public Official getReferee() {
		return referee;
	}

	public void setReferee(Official referee) {
		this.referee = referee;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public boolean isResultExists() {
		return resultExists;
	}

	public void setResultExists(boolean resultExists) {
		this.resultExists = resultExists;
	}

	public static int getMaxCompetitors() {
		return MAX_COMPETITORS;
	}

	public static int getMinCompetitors() {
		return MIN_COMPETITORS;
	}

}
