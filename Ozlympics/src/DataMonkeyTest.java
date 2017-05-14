import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataMonkeyTest {
	DataMonkey monkey;

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void testDataMonkey() {
		monkey = new DataMonkey(false);
		assertTrue(monkey.isDBConnected());
	}

//	@Test
//	public void testSaveParticipants() {
//		fail("Not yet implemented");
//	}
//
	// Test file reading
	@Test
	public void testLoadParticipants() {
		boolean useFile = true;
		monkey = new DataMonkey(useFile); 
		monkey.loadParticipants();
		assertTrue(monkey.getAthletes() != null);
//		System.out.println(monkey.getAthletes().get(0).toString());
//		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS")));
	}
	

//	@Test
//	public void testSaveResults() {
//		boolean useFile = true;
//		monkey = new DataMonkey(useFile); 
//		List <Game> games = new ArrayList<Game>(); 
//		games.add(new Game("Swimming", "S01"));
//		Athlete [] swimmers = monkey.getAthletes().subList(2, 10).toArray(new Athlete [8]);
//		try {
//			games.get(0).setAthletes(swimmers);
//		} catch (GameFullException e1) {
//			e1.printStackTrace();
//		}
//		games.get(0).setReferee(monkey.getOfficials().get(0));
//		for (int i = 0;i<8;i++) {
//			System.out.println(games.get(0).getAthletes()[i]);
//		}
//		
//		Athlete winner = null;
//		try {
//			winner = games.get(0).runGame();
//		} catch (NotEnoughAthletesException | NoRefereeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(winner + "start");
//		System.out.println(games.get(0).getScores());
//		String [] result = games.get(0).printResult();
//		System.out.println(games.get(0).getScores()[0]);
//		for( int i=0; i<result.length; i++) {
//			System.out.println(result[i]);
//		}
//		System.out.println();
//		
//		monkey.saveResults(games);
//
////		games[1] = new Game("Running", "R01");
////		games[1].setAthletes(Arrays.copyOfRange(athletes, 0, 4));
////		games[1].setReferee(officials[2]);		
////
////		games[2] = new Game("Cycling", "C01");
////		games[2].setAthletes(Arrays.copyOfRange(athletes, 12, 20));
////		games[2].setReferee(officials[1]);	
////
////		games[3] = new Game("Running", "R02");
////		games[3].setAthletes(Arrays.copyOfRange(athletes, 21, 28));
////		games[3].setReferee(officials[2]);	
//	}
	
	
	@Test	
	public void testGameListSaveResults() {
		boolean useFile = false;
		monkey = new DataMonkey(useFile); 
		Game glist = new Game("Swimming", "S01");
//		Athlete [] swimmers = monkey.getAthletes().subList(2, 10).toArray(new Athlete [8]);
		
		List<Athlete> swimmers = monkey.getAthletes().subList(2, 10);
		
//		System.out.println(swimmers);
		
		try {
			glist.addAthletes(swimmers);
		} catch (GameFullException e1) {
			e1.printStackTrace();
		}
		glist.setReferee(monkey.getOfficials().get(0));
//		for (int i = 0;i<8;i++) {
//			System.out.println(glist.getScoreAthlete().toString());
//		}
		
		try {
			glist.runGame();
		} catch (NotEnoughAthletesException | NoRefereeException e) {
			e.printStackTrace();
		}
		
//		System.out.println(glist.getScoreAthlete());

		List <Game> games = new ArrayList<Game>();
		games.add(glist);
		monkey.saveResults(games, false);

//		games[1] = new Game("Running", "R01");
//		games[1].setAthletes(Arrays.copyOfRange(athletes, 0, 4));
//		games[1].setReferee(officials[2]);		
//
//		games[2] = new Game("Cycling", "C01");
//		games[2].setAthletes(Arrays.copyOfRange(athletes, 12, 20));
//		games[2].setReferee(officials[1]);	
//
//		games[3] = new Game("Running", "R02");
//		games[3].setAthletes(Arrays.copyOfRange(athletes, 21, 28));
//		games[3].setReferee(officials[2]);	
	}

//	@Test
//	public void testLoadResults() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAthletes() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetAthletes() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetOfficials() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetOfficials() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetGames() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetGames() {
//		fail("Not yet implemented");
//	}

}
