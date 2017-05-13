import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class OfficialTest {
	private Official theBoss;
	private Athlete [] athletes;

	@Before
	public void setUp() throws Exception {
		theBoss = new Official("theboss", "The Boss", 27, "VIC");
		
		Scanner s = null;
		athletes = new Athlete[12];
        try {
            s = new Scanner(new BufferedReader(new FileReader("athletes.txt")));
            s.useDelimiter(",\\s*");
            int i = 0;

            while (s.hasNext()) {
            	String sport = s.next();

            	if (sport.equals("SuperAthlete")) 
            		athletes[i] = new Athlete(s.next(), s.next(), s.nextInt(), s.next());
            	else if (sport.equals("Swimmer")) 
            		athletes[i] = new Swimmer(s.next(), s.next(), s.nextInt(), s.next());
            	else
            		throw new Exception("Athlete type not found when attempting to create an athlete");
            	
            	System.out.println(athletes[i].toString());
            	i++;
           }
        } catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} finally {
            if (s != null) 
                s.close();
        }
	}

	@Test
	public void test() {
		int [] scores = new int [athletes.length];
		for(int i = 0; i<athletes.length; i++) {
			try {
				scores[i] = athletes[i].compete("Swimming");
			} catch (WrongSportException e) {}
		}
		
		for (int i=0; i< athletes.length; i++) {
			System.out.println(scores[i] + " " + athletes[i].getName());			
		}
				
		Athlete [] sorted = theBoss.scoreGame(athletes, scores);
		theBoss.awardPoints(sorted);
		
		for (int i=0; i< sorted.length; i++) {
			System.out.println(sorted[i].toString() + sorted[i].getPoints());
		}
	}
}
