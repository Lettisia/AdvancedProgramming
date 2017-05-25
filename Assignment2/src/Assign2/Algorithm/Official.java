package Assign2.Algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents the referee of an Ozlympics game. Officials are responsible for
 * awarding points for games.
 * 
 * @author Lettisia George
 */
public class Official extends Participant {
	// points for 1st, 2nd and 3rd place
	private static final int[] WINNINGPOINTS = { 5, 2, 1 };

	public Official(String userID, String name, int age, String state) {
		super(userID, name, age, state);
	}

	/**
	 * Orders athletes based on scores. Winner is athletes[0] when returned.
	 * 
	 * @param athletes
	 * @param scores
	 * @return ordered array of athletes
	 */
	public Athlete[] scoreGame(Athlete[] athletes, int[] scores) {
		Athlete[] ordered = new Athlete[athletes.length];

		for (int i = 0; i < athletes.length; i++) {
			int min = Arrays.stream(scores).min().getAsInt();
			int minIndex = find(scores, min);
			ordered[i] = athletes[minIndex];
			scores[minIndex] = Integer.MAX_VALUE; // no longer the minimum
		}
		return ordered;
	}

	/**
	 * Sorts list according to game scores, winner is first
	 * 
	 * @param pairList
	 *            - List of score-athlete pairs
	 */
	public void scoreGame(List<Pair<Integer, Athlete>> pairList) {
		Collections.sort(pairList);
	}

	/**
	 * Allocates points to winning athletes based on contents of WINNINGPOINTS.
	 * Assumes athletes are already sorted based on their results in a game.
	 * Winner is index 0.
	 * 
	 * @param athletes
	 *            Array of athletes sorted by score in a game
	 */
	public void awardPoints(Athlete[] athletes) {
		for (int i = 0; i < WINNINGPOINTS.length; i++) {
			athletes[i].setPoints(athletes[i].getPoints() + WINNINGPOINTS[i]);
		}
	}

	/**
	 * Allocates points to winning athletes based on contents of WINNINGPOINTS.
	 * Sorts Athletes based on their results in a game so scoreGame() method is
	 * unnecessary. Winner is index 0.
	 * 
	 * @param pairList
	 */
	public void awardPoints(List<Pair<Integer, Athlete>> pairList) {
		Collections.sort(pairList);
		for (int i = 0; i < WINNINGPOINTS.length; i++) {
			double newPoints = ((Athlete) pairList.get(i).getSecond()).getPoints() + WINNINGPOINTS[i];
			((Athlete) pairList.get(i).getSecond()).setPoints(newPoints);
		}
	}

	/**
	 * Method from StackOverflow. Accesssed: 24/03/2017
	 * URL:http://stackoverflow.com/questions/6171663/how-to-find-index-of-int-array-in-java-from-a-given-value
	 * 
	 * @param array
	 * @param value
	 * @return index of array with value.
	 * @author Jamie Curtis
	 * 
	 */
	private int find(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	public String printOfficialName() {
		return getName();
	}

	@Override
	public String getType() {
		return "official";
	}

}
