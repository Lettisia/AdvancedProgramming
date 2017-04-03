package ozlympics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Contains data for the Ozlympics game.
 * Reads data in from text files containing athletes and officials. 
 * Creates arrays of athletes, officials and games. 
 * 
 * @author Lettisia George
 *
 */
public class Data {
	private Athlete [] athletes;
	private Official [] officials;
	private Game [] games;
	private String athleteFile = "athlete.txt";
	private String officialFile = "officials.txt";
	
	public Data() {
		Scanner s = null;
		athletes = new Athlete[28];
		officials = new Official[3];
		
		// Grab the athlete data
        try {
            int i = 0;
            s = new Scanner(new BufferedReader(new FileReader(athleteFile)));
            s.useDelimiter(",\\s*");

            while (s.hasNext()) {
            	String sport = s.next();
            	if (sport.equals("SuperAthlete")) {
            		athletes[i] = new Athlete(s.next(), s.next(), s.nextInt(), s.next());
            	} else if (sport.equals("Swimmer")) {
            		athletes[i] = new Swimmer(s.next(), s.next(), s.nextInt(), s.next());
            	} else if (sport.equals("Cyclist")) {
                	athletes[i] = new Cyclist(s.next(), s.next(), s.nextInt(), s.next());
            	} else if (sport.equals("Runner")) {
               		athletes[i] = new Runner(s.next(), s.next(), s.nextInt(), s.next());
            	} else {
            		throw new Exception("Athlete type not found when attempting to create an athlete");
            	}
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
        
        // Grab the Officials Data
        try {
        	int i = 0;
        	s = new Scanner(new BufferedReader(new FileReader(officialFile)));
            s.useDelimiter(",\\s*");
            while (s.hasNext()) {
            	officials[i] = new Official(s.next(), s.next(), s.nextInt(), s.next());
            	i++;
            }
        } catch (FileNotFoundException e) {
        	System.out.println(e.toString());
        	return;
		} finally {
            if (s != null) {
                s.close();
            }
        }
        
        //Setup some games
        games = new Game [4]; 
		games[0] = new Game("Swimming", "S01");
		games[0].setAthletes(Arrays.copyOfRange(athletes, 2,12));
		games[0].setReferee(officials[0]);
		
		games[1] = new Game("Running", "R01");
		games[1].setAthletes(Arrays.copyOfRange(athletes, 0, 4));
		games[1].setReferee(officials[2]);		
		
		games[2] = new Game("Cycling", "C01");
		games[2].setAthletes(Arrays.copyOfRange(athletes, 12, 20));
		games[2].setReferee(officials[1]);	
		
		games[3] = new Game("Running", "R02");
		games[3].setAthletes(Arrays.copyOfRange(athletes, 21, 28));
		games[3].setReferee(officials[2]);	
	}
	
	public Athlete [] getAthletes() {
		return athletes;
	}

	public Official [] getOfficials() {
		return officials;
	}

	public Game [] getGames() {
		return games;
	}
}