package ozlympics;

/**
 * Main class which reads in data and starts game.
 * 
 * @author Lettisia George
 */
public class Ozlympics {
	public static void main(String[] args) {
		Data myData = new Data();
		GameDriver myGame = new GameDriver(myData.getGames(), myData.getOfficials(), myData.getAthletes());
		myGame.startMenu();	
	}
}
