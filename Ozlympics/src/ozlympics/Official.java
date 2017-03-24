package ozlympics;

import java.util.Arrays;

public class Official extends Participant {
	private static final int[] WINNINGPOINTS = {5,2,1}; //points for 1st, 2nd and 3rd place

	public Official(String userID, String name, int age, String state) {
		super(userID, name, age, state);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Orders athletes based on scores. Winner is athletes[0]. 
	 * 
	 * @param athletes
	 * @param scores
	 * @return ordered array of athletes
	 */
	public Athlete [] scoreGame(Athlete [] athletes, int [] scores) {
		Athlete [] ordered = new Athlete[athletes.length];
		
		for (int i=0; i<athletes.length; i++) {
			int min = Arrays.stream(scores).min().getAsInt();
			int minIndex = find(scores, min);
			ordered[i] = athletes[minIndex];
			scores[minIndex] = Integer.MAX_VALUE;   //no longer the minimum	
		}
		
		return ordered;
	}
	
	
	/**
	 * Allocates points to winning athletes based on contents of WINNINGPOINTS
	 * @param athletes Array of athletes sorted by score in a game
	 * @pre Assumes athletes are sorted based on their results in a game. Winner is index 0.
	 */
	public void awardPoints(Athlete [] athletes) {
		for( int i=0; i<WINNINGPOINTS.length; i++) {
			athletes[i].setPoints(athletes[i].getPoints() + WINNINGPOINTS[i]);
		}
	}
	
	/**
	 * @param array
	 * @param value
	 * @return index of array with value.
	 * From StackOverflow
	 * @author Jamie CUrtis
	 * Accesssed: 24/03/2017
	 * URL: http://stackoverflow.com/questions/6171663/how-to-find-index-of-int-array-in-java-from-a-given-value
	 */
	private int find(int[] array, int value) {
	    for(int i=0; i<array.length; i++) {
	         if(array[i] == value)
	             return i;
	    }
	    return -1;
	}

}

