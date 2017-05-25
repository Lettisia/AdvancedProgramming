package Assign2.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Contains data, keeps track of games and is complementary of controller classes.
 * 
 * Author Théo Dufort
 *
 */
public class Driver {
	private static DataMonkey myData = new DataMonkey(true);
	private List<Game> games;
	private List<Official> officials;
	private List<Athlete> athletes;
	private List<String> gameTypes;
	private List<Athlete> swimmers;
	private List<Athlete> runners;
	private List<Athlete> cyclists;
	private Game currentGame = null;

	/*
	 * Constructor of Driver Class
	 */
	public Driver() {
		games = new ArrayList<Game>();
		officials = myData.getOfficials();
		athletes = myData.getAthletes();
		gameTypes = new ArrayList<String>();
		gameTypes.add("Swimming Event");
		gameTypes.add("Running Event");
		gameTypes.add("Cycling Event");
		gameTypes.add("Random Event");
		swimmers = new ArrayList<Athlete>();
		swimmers.addAll(isolateAthletes("swimmer"));
		swimmers.addAll(isolateAthletes("super"));
		runners = new ArrayList<Athlete>();
		runners.addAll(isolateAthletes("sprinter"));
		runners.addAll(isolateAthletes("super"));
		cyclists = new ArrayList<Athlete>();
		cyclists.addAll(isolateAthletes("cyclist"));
		cyclists.addAll(isolateAthletes("super"));
	}

	/*
	 * Gives ID to game and adds to list of played games
	 */
	public void addGameToList(Game game) {

		String letter = null;
		int number;
		if (game.getWhichSport().equals("Swimming")) {
			letter = "S";
		} else if (game.getWhichSport().equals("Cycling")) {
			letter = "C";
		} else if (game.getWhichSport().equals("Running")) {
			letter = "R";
		}
		if (games == null)
			number = 1;
		else {
			number = games.size() + 1;
		}
		game.setGameID(letter + Integer.toString(number));
		games.add(game);
		myData.saveResults(games, false);
	}

	/*
	 * Given a list of athletes, return list of their names
	 */
	public List<String> getAthletesNames(List<Athlete> athleteslist) {
		List<String> list = new ArrayList<String>();
		for (Athlete athlete : athleteslist) {
			list.add(athlete.printAthleteName());
		}
		return list;
	}

	/*
	 * Given a list of officials, return list of their names
	 */
	public List<String> getRefereesNames(List<Official> refereelist) {
		List<String> list = new ArrayList<String>();
		for (Official referee : refereelist) {
			list.add(referee.printOfficialName());
		}
		return list;
	}

	/*
	 * Given indices, returns corresponding athletes from a given athletes list
	 */
	public void selectAthwithInt(List<Athlete> athletes, List<Integer> indices) throws GameFullException {
		List<Athlete> athletesSelected = new ArrayList<Athlete>();
		for (int indice : indices) {
			athletesSelected.add(athletes.get(indice));
		}
		getCurrentGame().addAthletes(athletesSelected);
	}

	/*
	 * Given an index, returns corresponding official from officials list
	 */
	public void selectOfficialwithInt(List<Integer> indices) throws GameFullException {
		Official officialSelected = null;
		for (int index : indices) {
			officialSelected = (officials.get(index));
		}
		getCurrentGame().setReferee(officialSelected);
	}

	/*
	 * Returns all athletes of one given type in a list
	 */
	public List<Athlete> isolateAthletes(String type) {
		List<Athlete> isolatedAthletes = new ArrayList<Athlete>();
		for (Athlete athlete : athletes) {
			if (athlete.getType().equals(type))
				isolatedAthletes.add(athlete);
		}
		return isolatedAthletes;
	}

	/*
	 * Displays results for all games played
	 */
	public String displayResults() {
		String results = "";

		for (Game game : games) {
			results = results + game.printResult() + "\n";
		}
		if (results.equals("")) {
			return "No results yet.";
		} else
			return results;
	}

	/*
	 * Displays overall score of all athletes
	 */
	public String printPlayerPoints() {
		String temp = "";
		Collections.sort(athletes);
		for (Athlete ath : athletes) {
			if (ath.getPoints() > 0) {
				temp = temp + ath.getName() + " ... " + (int) ath.getPoints() + " points\n";
			}
		}
		temp = temp + "\nOther Athletes Have \nCurrently No Points.";
		return temp;
	}

	/*
	 * setters and getters
	 */

	public List<Official> getOfficials() {
		return officials;
	}

	public void setOfficials(List<Official> officials) {
		this.officials = officials;
	}

	public List<Athlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(List<Athlete> athletes) {
		this.athletes = athletes;
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public List<String> getGameTypes() {
		return gameTypes;
	}

	public void setGameTypes(List<String> gameTypes) {
		this.gameTypes = gameTypes;
	}

	public List<Athlete> getSwimmers() {
		return swimmers;
	}

	public void setSwimmers(List<Athlete> swimmers) {
		this.swimmers = swimmers;
	}

	public List<Athlete> getRunners() {
		return runners;
	}

	public void setRunners(List<Athlete> runners) {
		this.runners = runners;
	}

	public List<Athlete> getCyclists() {
		return cyclists;
	}

	public void setCyclists(List<Athlete> cyclists) {
		this.cyclists = cyclists;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

}
