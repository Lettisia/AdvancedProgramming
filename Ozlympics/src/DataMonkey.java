import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


/**
 * Contains data for the Ozlympics game.
 * Reads data in from text files containing athletes and officials. 
 * Creates arrays of athletes, officials and games. 
 * 
 * @author Lettisia George
 *
 */

public class DataMonkey {
	private static final String PARTICIPANTFILE = "participants.txt";
	private static final String RESULTSFILE = "gameResults.txt";

	private List <Athlete> athletes = new ArrayList<Athlete>();
	private List <Official> officials = new ArrayList<Official>();
	private List <Game> games = new ArrayList<Game>();

	private boolean isDBConnected = false;



	public DataMonkey() {
		//Check for DB connection
		try { //opening the db
			Connection c = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			//hey it worked!
			isDBConnected = true;
			c.close();
		} catch (Exception e) {
			isDBConnected = false;
		}

		loadParticipants();
		loadResults();
	}

	public DataMonkey(boolean useFile) {
		if (!useFile) {
			//Check for DB connection
			try { //opening the db
				Connection c = null;
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
				c.close();
				isDBConnected = true;
			} catch (Exception e) {
				isDBConnected = false;
			}
		} else {
			isDBConnected = false;
		}

		loadParticipants();
		//		loadResults();			// not needed as results are new for each time game is run
	}

	public void saveParticipants() {
		if (isDBConnected) {
			saveDBParticipants();
		} else {
			writeParticipantFile();
		}
	}

	public void loadParticipants() {
		if (isDBConnected) {
			readDBParticipants();
		} else {
			readParticipantFile();
		}
	}

	public void saveResults(List <Game> games) {
		if (isDBConnected) {
			saveDBResults(games);
		} else {
			writeResultsFile(games);
		}
	}

	public void loadResults() {
		if (isDBConnected) {
			readDBResults();
		} else {
			readResultsFile();
		}
	}

	public void readParticipantFile() {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(PARTICIPANTFILE)));

			while (s.hasNext()) {
				String line = s.nextLine();
				String [] tokens = line.split(",");
				if (tokens[1].equals("official")) {
					officials.add(new Official(tokens[0], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
				} else if (tokens[1].equals("swimmer")) {
					athletes.add(new Swimmer(tokens[0], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
				} else if (tokens[1].equals("sprinter")) {
					athletes.add(new Runner(tokens[0], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
				} else if (tokens[1].equals("super")) {
					athletes.add(new Athlete(tokens[0], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
				} else if (tokens[1].equals("cyclist")) {
					athletes.add(new Cyclist(tokens[0], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
				} else {
					throw new WrongSportException();
				}            	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	public void readResultsFile () {

	}

	public void writeParticipantFile() {

	}

	public void writeResultsFile(List <Game> games) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(RESULTSFILE);
			
			for (ListIterator<Game> it = games.listIterator(); it.hasNext();) {
				Game game = it.next();
				String nowString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
				writer.println(game.getGameID() + "," + game.getReferee().getUserID() + "," + nowString);
				

				
				for (int i = 0; i < game.getAthletes().length; i++) {
					Athlete player = game.getAthletes()[i];
					writer.println(player.getUserID() + "," + game.getScores()[i] + "," + player.getPoints());
				}
				
				writer.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            if (writer != null) {
                writer.close();
            }
        }
	}

	public void saveDBParticipants() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveDBResults(List <Game> games) {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void readDBParticipants() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void readDBResults() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the athletes
	 */
	public List <Athlete> getAthletes() {
		return athletes;
	}

	/**
	 * @param athletes the athletes to set
	 */
	public void setAthletes(List <Athlete> athletes) {
		this.athletes = athletes;
	}

	/**
	 * @return the officials
	 */
	public List <Official> getOfficials() {
		return officials;
	}

	/**
	 * @param officials the officials to set
	 */
	public void setOfficials(List <Official> officials) {
		this.officials = officials;
	}

	/**
	 * @return the games
	 */
	public List <Game> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(List <Game> games) {
		this.games = games;
	}

	public boolean isDBConnected() {
		return isDBConnected;
	}

	public void setDBConnected(boolean isDBConnected) {
		this.isDBConnected = isDBConnected;
	}
}
