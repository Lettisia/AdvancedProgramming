package ozlympics;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class GameDriverTest {
	private static Athlete [] athletes;
	private static Official [] officials;
	private static Game [] games;
	private GameDriver myGame;
	
	@Before
	public void setUp() throws Exception {
		Scanner s = null;
		athletes = new Athlete[12];
		officials = new Official[3];
        try {
            s = new Scanner(new BufferedReader(new FileReader("athletes.txt")));
            s.useDelimiter(",\\s*");
            int i = 0;

            while (s.hasNext()) {
            	String sport = s.next();

            	if (sport.equals("SuperAthlete")) {
            		athletes[i] = new Athlete(s.next(), s.next(), s.nextInt(), s.next());
            	}
            	else if (sport.equals("Swimmer")) {
            		athletes[i] = new Swimmer(s.next(), s.next(), s.nextInt(), s.next());
           		}
            	else
            		throw new Exception("Athlete type not found when attempting to create an athlete");
            	
//            	System.out.println(athletes[i].toString());
            	i++;
           }
        } catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} finally {
            if (s != null) {
                s.close();
            }
        }
        
         try {
            s = new Scanner(new BufferedReader(new FileReader("officials.txt")));
            s.useDelimiter(",\\s*");
            int i = 0;

            while (s.hasNext()) {
            	officials[i] = new Official(s.next(), s.next(), s.nextInt(), s.next());
//            	System.out.println(officials[i].toString());
            	i++;
           }
        }
        catch (FileNotFoundException e) {
        	System.out.println(e.toString());
        	return;
		} finally {
            if (s != null) {
                s.close();
            }
        }
        
        games = new Game [2]; 
		games[0] = new Game("Swimming", "S01");
		games[0].setAthletes(Arrays.copyOfRange(athletes, 4,12));
		games[0].setReferee(officials[0]);
		
		games[1] = new Game("Running", "R01");
		games[1].setAthletes(Arrays.copyOfRange(athletes, 0, 4));
		games[1].setReferee(officials[1]);		
		
//		System.out.println(games[1].printCompetitorNames()[0]);
//		System.out.println(games[1].printCompetitorNames()[1]);
//		System.out.println(games[1].printCompetitorNames()[2]);
//		System.out.println(games[1].numAthletes());
//		System.out.println(games[0].numAthletes());

		myGame = new GameDriver(games, officials, athletes);
	}

	@Test
	public void test() {	
//		myGame.setCurrentGame(myGame.chooseGame());
//		System.out.println(myGame.getCurrentGame().toString());
		myGame.setCurrentGame(games[0]);
//		myGame.setUserPrediction(myGame.predictWinner());
		System.out.println(myGame.getUserPrediction());
	}
}
