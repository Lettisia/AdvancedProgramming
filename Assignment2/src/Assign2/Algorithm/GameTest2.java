package Assign2.Algorithm;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameTest2 {
	DataMonkey monkey;
	Game game;
	
	@Before
	public void setUp() throws Exception {
		monkey = new DataMonkey(); 
		game = new Game("Swimming", "S01");
	}
	
	@Test
	public void printTest() throws NotEnoughAthletesException, NoRefereeException {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);
		try {
			game.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		game.setReferee(monkey.getOfficials().get(0));
		game.runGame();
		System.out.println(game.printResult());
		System.out.println(game.printCompetitorResults());
	}

	@Test
	public void testNumAthletes() {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);
		try {
			game.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		game.setReferee(monkey.getOfficials().get(0));
		assertEquals(game.numAthletes(), 8);
	}

	@Test(expected = NotEnoughAthletesException.class)
	public void testNotEnoAth() throws NotEnoughAthletesException, NoRefereeException {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 4);
		try {
			game.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		game.setReferee(monkey.getOfficials().get(0));
		game.runGame();
	}
	
	@Test(expected = GameFullException.class)
	public void testGameFullEx() throws NotEnoughAthletesException, NoRefereeException, GameFullException {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 12);
		game.addAthletes(swimmers);
		game.setReferee(monkey.getOfficials().get(0));
		game.runGame();
	}
	
	@Test(expected = NoRefereeException.class)
	public void testNoRef() throws NotEnoughAthletesException, NoRefereeException {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);
		try {
			game.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		game.runGame();
		
	}
		

	@Test
	public void testIsAthlete() {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);
		try {
			game.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		assertTrue(game.isAthlete("Merry"));
	}

	@Test
	public void testFindAthlete() {
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);
		try {
			game.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		game.setReferee(monkey.getOfficials().get(0));
		
		assertEquals(swimmers.get(0), game.findAthlete(swimmers.get(0).getUserID()));
	}

}
