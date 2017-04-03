package ozlympics;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Lettisia George
 *
 */
public class GameTest {
	private static Athlete [] athletes;
	private static Official [] officials;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Scanner s = null;
		athletes = new Athlete[12];
		officials = new Official[3];
        try {
            int i = 0;
            s = new Scanner(new BufferedReader(new FileReader("athletes.txt")));
            s.useDelimiter(",\\s*");

            while (s.hasNext() && i < 12) {
            	String sport = s.next();
            	if (sport.equals("SuperAthlete")) {
            		athletes[i] = new Athlete(s.next(), s.next(), s.nextInt(), s.next());
            	} else if (sport.equals("Swimmer")) {
            		athletes[i] = new Swimmer(s.next(), s.next(), s.nextInt(), s.next());
           		} else {
           			throw new Exception("Athlete type not found when attempting to create an athlete");
           		}
            	System.out.println(athletes[i].toString());
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
            	System.out.println(officials[i].toString());
            	i++;
           }
		} finally {
            if (s != null) {
                s.close();
            }
        }
	}

	@Test
	public void test() throws Exception {
		Game myGame = new Game();
		myGame.setAthletes(Arrays.copyOfRange(athletes, 2, 12));
		assertEquals(8, myGame.numAthletes());
		
		myGame.setReferee(officials[0]);
		myGame.setGameID("S01");
		myGame.setWhichSport("Swimming");
		
		String [] nameList = myGame.printCompetitorNames();
		
		for(int i=0; i<nameList.length; i++) {
			System.out.println(nameList[i]);
		}
		
		String [] result = myGame.printResult();
		for(int j=0; j<result.length; j++) {
			System.out.println(result[j]);
		}
		
//		myGame.setResultExists(true);
//		
//		result = myGame.printResult();
//		for(int j=0; j<result.length; j++) {
//			System.out.println(result[j]);
//		}
	
		result = myGame.printResult();
		for(int j=0; j<result.length; j++) {
			System.out.println(result[j]);
		}
		result = myGame.printCompetitorNames();
		for(int j=0; j<result.length; j++) {
			System.out.println(result[j]);
		}
	}
}
