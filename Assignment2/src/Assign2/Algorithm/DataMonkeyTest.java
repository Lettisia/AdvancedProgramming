package Assign2.Algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DataMonkeyTest {
	DataMonkey monkey;

	@Test
	public void testDataMonkey() {
		monkey = new DataMonkey(false);
		assertTrue(monkey.isDBConnected());
	}

	@Test
	public void testLoadParticipants() {
		boolean useFile = true;
		monkey = new DataMonkey(useFile);
		monkey.loadParticipants();
		assertTrue(monkey.getAthletes() != null);
	}

	@Test
	public void testGameListSaveResultsFile() {
		boolean useFile = true;
		monkey = new DataMonkey(useFile);
		Game glist = new Game("Swimming", "S01");
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);

		try {
			glist.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		glist.setReferee(monkey.getOfficials().get(0));

		try {
			glist.runGame();
		} catch (NotEnoughAthletesException | NoRefereeException e) {
			e.printStackTrace();
		}

		List<Game> games = new ArrayList<Game>();
		games.add(glist);
		monkey.saveResults(games, false);
	}

	@Test
	public void testGameListSaveResultsDB() {
		boolean useFile = false;
		monkey = new DataMonkey(useFile);
		Game glist = new Game("Swimming", "S01");

		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);

		try {
			glist.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		glist.setReferee(monkey.getOfficials().get(0));

		try {
			glist.runGame();
		} catch (NotEnoughAthletesException | NoRefereeException e) {
			e.printStackTrace();
		}

		List<Game> games = new ArrayList<Game>();
		games.add(glist);
		monkey.saveResults(games, false);
	}
}
