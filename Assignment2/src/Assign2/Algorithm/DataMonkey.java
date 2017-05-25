package Assign2.Algorithm;

import java.io.BufferedReader;
import java.io.FileOutputStream;
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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Contains and manages data for the Ozlympics game. Reads/writes data in from
 * text files containing participants and game results. Access and stores
 * participant data in a SQLite database.
 * 
 * @author Lettisia George
 *
 */

public class DataMonkey {
	private static final String PARTICIPANTFILE = "participants.txt";
	private static final String RESULTSFILE = "gameResults.txt";
	private List<Athlete> athletes = new ArrayList<Athlete>();
	private List<Official> officials = new ArrayList<Official>();
	private List<Game> games = new ArrayList<Game>();
	private boolean isDBConnected = false;

	/*
	 * To set up db participant table and populate from text file create a new
	 * DataMonkey once with isDBsetup = false
	 */
	private boolean isDBSetup = true;

	public DataMonkey() {
		// Check for DB connection
		try { // opening the db
			Connection c = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			if (!isDBSetup) {
				setupDB(c);
			}
			c.close();
			isDBConnected = true;
		} catch (Exception e) {
			isDBConnected = false;
		}
		loadParticipants();
	}

	public DataMonkey(boolean useFile) {
		if (!useFile) {
			try {
				Connection c = null;
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
				if (!isDBSetup) {
					setupDB(c);
				}
				c.close();
				isDBConnected = true;
			} catch (Exception e) {
				isDBConnected = false;
			}
		} else {
			isDBConnected = false;
		}
		loadParticipants();
	}

	/**
	 * Sets up the participants table if it does not exist and populates it from
	 * the text file.
	 * 
	 * @param c
	 *            open database connection
	 * @throws SQLException
	 */
	private void setupDB(Connection c) throws SQLException {
		readParticipantFile();

		if (!tableExist(c, "participants")) {
			createParticipantTable(c);
		}

		List<Participant> ppl = new ArrayList<Participant>();
		ListIterator<Athlete> it = athletes.listIterator();
		while (it.hasNext()) {
			ppl.add(it.next());
		}

		ListIterator<Official> it2 = officials.listIterator();
		while (it2.hasNext()) {
			ppl.add(it2.next());
		}
		saveDBParticipants(ppl);
	}

	/**
	 * Loads the list of participants from a database or file depending on what
	 * is currently available.
	 */
	public void loadParticipants() {
		if (isDBConnected) {
			readDBParticipants();
		} else {
			readParticipantFile();
		}
	}

	/**
	 * Saves a list of game results in a database or file depending on what is
	 * currently available.
	 * 
	 * @param games
	 *            List of games with results
	 * @param append
	 *            If false any previously stored results will be deleted before
	 *            the new results are added.
	 */
	public void saveResults(List<Game> games, boolean append) {
		if (isDBConnected) {
			saveDBResults(games, append);
		} else {
			writeResultsFile(games, append);
		}
	}

	/**
	 * Loads the list of participants from a text file
	 */
	public void readParticipantFile() {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(PARTICIPANTFILE)));

			while (s.hasNext()) {
				String line = s.nextLine();
				String[] tokens = line.split(",");

				// check for missing data - missing id, missing type
				if (!tokens[0].isEmpty() & !tokens[1].isEmpty() & !isAParticipant(tokens[0])) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	/**
	 * Helper method for finding a participant by username
	 * 
	 * @param userID
	 * @return true if found in either list of officials or athletes
	 */
	public boolean isAParticipant(String userID) {
		boolean found = false;
		Iterator<Athlete> it = athletes.iterator();
		while (it.hasNext() & !found) {
			found = it.next().getUserID().equals(userID);
		}
		Iterator<Official> it1 = officials.iterator();
		while (it1.hasNext() & !found) {
			found = it1.next().getUserID().equals(userID);
		}
		return found;
	}

	/**
	 * Saves a list of game results in a file.
	 * 
	 * @param games
	 *            List of games with results
	 * @param append
	 *            If false existing results in file will be deleted
	 */
	public void writeResultsFile(List<Game> games, boolean append) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(RESULTSFILE, append));
			if (append) {
				writer.println();
			}

			for (ListIterator<Game> it = games.listIterator(); it.hasNext();) {

				Game game = it.next();
				String nowString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
				writer.println(game.getGameID() + "," + game.getReferee().getUserID() + "," + nowString);

				List<Pair<Integer, Athlete>> list = game.getScoreAthlete();

				ListIterator<Pair<Integer, Athlete>> it2 = list.listIterator();
				Pair<Integer, Athlete> temp;
				while (it2.hasNext()) {
					temp = it2.next();
					writer.println(
							temp.getSecond().getUserID() + "," + temp.getFirst() + "," + temp.getSecond().getPoints());
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

	/**
	 * Saves a list of participants in a database. Adds participants to those
	 * already in database
	 * 
	 * @param ppl
	 *            list of athletes and officials
	 */
	public void saveDBParticipants(List<Participant> ppl) {
		Connection c = null;
		Statement s = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);

			if (!tableExist(c, "participants")) {
				createParticipantTable(c);
			}

			s = c.createStatement();
			ListIterator<Participant> it = ppl.listIterator();
			Participant temp;
			String tempString;

			while (it.hasNext()) {
				temp = it.next();
				tempString = "\"" + temp.getUserID() + "\", \"" + temp.getType() + "\", \"" + temp.getName() + "\", "
						+ temp.getAge() + ", \"" + temp.getState() + "\"";
				s.executeUpdate("insert into participants values (" + tempString + ")");
			}
			c.commit();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves a list of game results in a database.
	 * 
	 * @param games
	 *            List of games with results
	 * @param append
	 *            If false existing results in database will be deleted
	 */
	public void saveDBResults(List<Game> games, boolean append) {
		Connection c = null;
		Statement s = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);

			if (!tableExist(c, "results")) {
				createResultTable(c);
			}

			s = c.createStatement();
			if (!append) {
				s.executeUpdate("delete from results;");
			}

			ListIterator<Game> gameIt = games.listIterator();
			Game aGame;
			String gameString;
			String athleteString;

			while (gameIt.hasNext()) {
				aGame = gameIt.next();
				gameString = "\"" + aGame.getGameID() + "\", \"" + aGame.getReferee().getUserID() + "\"";

				List<Pair<Integer, Athlete>> scoreAthlete = aGame.getScoreAthlete();
				ListIterator<Pair<Integer, Athlete>> pairIt = scoreAthlete.listIterator();
				Pair<Integer, Athlete> aPair;
				while (pairIt.hasNext()) {
					aPair = pairIt.next();
					athleteString = "\"" + aPair.getSecond().getUserID() + "\", " + aPair.getFirst() + ", "
							+ aPair.getSecond().getPoints();
					s.executeUpdate("insert into results values (" + gameString + ", " + athleteString + ")");
				}
			}
			c.commit();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the list of participants (athletes and officials) from a database.
	 */
	public void readDBParticipants() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			conn.setAutoCommit(false);

			if (tableExist(conn, "participants")) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from participants;");

				while (rs.next()) {
					if (rs.getString("type").equals("official")) {
						officials.add(new Official(rs.getString("userID"), rs.getString("name"), rs.getInt("age"),
								rs.getString("state")));
					} else if (rs.getString("type").equals("swimmer")) {
						athletes.add(new Swimmer(rs.getString("userID"), rs.getString("name"), rs.getInt("age"),
								rs.getString("state")));
					} else if (rs.getString("type").equals("sprinter")) {
						athletes.add(new Runner(rs.getString("userID"), rs.getString("name"), rs.getInt("age"),
								rs.getString("state")));
					} else if (rs.getString("type").equals("super")) {
						athletes.add(new Athlete(rs.getString("userID"), rs.getString("name"), rs.getInt("age"),
								rs.getString("state")));
					} else if (rs.getString("type").equals("cyclist")) {
						athletes.add(new Cyclist(rs.getString("userID"), rs.getString("name"), rs.getInt("age"),
								rs.getString("state")));
					} else {
						throw new WrongSportException();
					}
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the table in the database used to store participants
	 * 
	 * @param conn
	 *            - An open database connection.
	 * @throws SQLException
	 */
	private void createParticipantTable(Connection conn) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		stmt.executeUpdate("create table participants " + "(userID varchar(20) not null, " + "type varchar(20), "
				+ "name varchar(30), " + "age int, " + "state varchar(20), " + "primary key(userID));");
		conn.commit();
	}

	/**
	 * Creates the table in the database used to store game results
	 * 
	 * @param conn
	 *            - An open database connection.
	 * @throws SQLException
	 */
	private void createResultTable(Connection conn) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		stmt.executeUpdate("create table results " + "(gameID varchar(20) not null, " + "officialID varchar(20), "
				+ "athleteID varchar(20) not null, " + "result int, " + "points numeric, "
				+ "primary key(gameID,athleteID));");
		conn.commit();
	}

	// Probably not necessary
	public void readDBResults() {
		// TODO needed?
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * From StackOverflow
	 * http://stackoverflow.com/questions/2942788/check-if-table-exists
	 * 
	 * @author peterh
	 * 
	 * @param conn
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static boolean tableExist(Connection conn, String tableName) throws SQLException {
		boolean tExists = false;
		try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
			while (rs.next()) {
				String tName = rs.getString("TABLE_NAME");
				if (tName != null && tName.equals(tableName)) {
					tExists = true;
					break;
				}
			}
		}
		return tExists;
	}

	/**
	 * @return the athletes
	 */
	public List<Athlete> getAthletes() {
		return athletes;
	}

	/**
	 * @param athletes
	 *            the athletes to set
	 */
	public void setAthletes(List<Athlete> athletes) {
		this.athletes = athletes;
	}

	/**
	 * @return the officials
	 */
	public List<Official> getOfficials() {
		return officials;
	}

	/**
	 * @param officials
	 *            the officials to set
	 */
	public void setOfficials(List<Official> officials) {
		this.officials = officials;
	}

	/**
	 * @return the games
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * @param games
	 *            the games to set
	 */
	public void setGames(List<Game> games) {
		this.games = games;
	}

	/**
	 * Used to control whether data is stored/accessed from the text file or
	 * database.
	 * 
	 * @return true if there is a connection to a database available
	 */
	public boolean isDBConnected() {
		return isDBConnected;
	}

	/**
	 * Used to control whether data is stored/accessed from the text file or
	 * database.
	 * 
	 * @param isDBConnected
	 */
	public void setDBConnected(boolean isDBConnected) {
		this.isDBConnected = isDBConnected;
	}
}
