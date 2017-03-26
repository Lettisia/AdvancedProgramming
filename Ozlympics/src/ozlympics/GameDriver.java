/**
 * 
 * Not yet implemented
 * - Option to cancel a menu item once selected and return to main menu.
 * - startGame() method to run games
 * - Nice menu messages like 'Welcome to Ozlympics' etc.
 * 
 * Assumption:
 * - the user can only predict the result of the current game. A new prediction will replace the old one.
 * 
 * @author lettisia
 *
 */

package ozlympics;
import java.util.Scanner;

public class GameDriver {
	private static Scanner keyboard = new Scanner(System.in);	
	private Game [] games;
	private Official [] officials;
	private Athlete [] athletes;
	private Game currentGame;
	private Athlete userPrediction;

	public GameDriver(Game [] games, Official [] officials, Athlete [] athletes) {
		this.games = games;
		this.setOfficials(officials);
		this.setAthletes(athletes);
		userPrediction = null;
		currentGame = null;
	}
	
	// Method for menu system and game play
	public void startMenu() {
		System.out.println("Welcome to Ozlympics!");
		
		int choice;
		do {
			choice = menuChoice();
			
			if (choice == 1) {
				currentGame = chooseGame();
				askToContinue();
			}
			else if (choice == 2) {
				setUserPrediction(predictWinner());
				askToContinue();
			}
			else if (choice == 3) {
				startGame();
				askToContinue();
			}
			else if (choice == 4) {
				displayResults();
				askToContinue();
			}
			else if (choice == 5) {
				displayPoints();
				askToContinue();
			}
		} while (choice != 6);
		
		System.out.println();
		System.out.println("Thank you for playing Ozlympics. Have a great day!");
		if (keyboard != null) {
				keyboard.close();
		}
	}
	
	/**
	 * Displays the name and points of each athlete to the console
	 */
	private void displayPoints() {
		for (int i=0; i<athletes.length; i++) {
			System.out.println(athletes[i].getName() + " received " + athletes[i].getPoints() + " points.");
		}
	}
	
	/**
	 * Waits until the user presses enter to move on
	 */
	private void askToContinue() {
		System.out.println("Press enter to continue ");
		try {
			System.in.read();
		} catch(Exception e) {}
	}
	
	/**
	 * Displays the results of each game to the console
	 */
	private void displayResults() {
		for (int i = 0; i< games.length; i++) {
			String [] result = games[i].printResult();
			for(int j=0; j<result.length; j++) {
				System.out.println(result[j]);
			}		
			System.out.println();
		}
	}

	/**
	 * Runs the current game
	 */
	private void startGame() {
		System.out.println();
		if (currentGame == null) {
			System.out.println("There is no game selected. Please select a game.");
		} else {
			if (userPrediction != null) {
				System.out.println("You have predicted that " + userPrediction.getName() + " will win.");
//				askToContinue();
			}
			Athlete winner = currentGame.runGame();
			System.out.println();
			String [] result = currentGame.printResult();
			for( int i=0; i<result.length; i++) {
				System.out.println(result[i]);
			}
			System.out.println();
			if (userPrediction != null) {
				if(winner == userPrediction) { 
					System.out.println("Congratulations! You correctly predicted the winner.");
					System.out.println();
				} else { 
					System.out.println("Sorry, your prediction was incorrect.");
					System.out.println();
				}
				userPrediction = null; // User prediction can only be used for one game
			}
		}
	}

	/**
	 * Asks the user to select one of the athletes in the current game
	 * @return the Athlete predicted to win by the user
	 */
	private Athlete predictWinner() {
		Athlete newWinner = null;
		boolean done = false;

		if (currentGame == null) {
			System.out.println("There is no game selected. Please select a game.");
			return null;
		} else {	
			System.out.println();
			System.out.println("The following athletes are competing in a " + currentGame.getWhichSport() + " race.");
			String [] nameList = currentGame.printCompetitorNames();

			for(int i=0; i<nameList.length; i++) {
				System.out.println(nameList[i]);
			}

			System.out.println();
			System.out.println("Who will win? ");

			do {
				System.out.println("");
				System.out.print("Enter the winner's number: " );

				while (!keyboard.hasNextInt()) {
					System.out.print("Enter the winner's number: ");	
					keyboard.next();
				}
				
				int response = keyboard.nextInt();
				response--;
				
				if (response >= 0 && response < currentGame.numAthletes()) {
					newWinner = currentGame.getAthletes()[response];

					System.out.println();
					System.out.println("You have selected " + newWinner.getName() + ". Good luck! ");
					done = true;
				}
				
				if (done == false) {
					System.out.println("I couldn't find that Athlete.");
				}
			} while (!done);
			
			System.out.println();
			return newWinner;
		}
	}

	/**
	 * Displays a menu so the user can select a game to run
	 * @return the selected game
	 */
	private Game chooseGame() {
		boolean done = false;
		Game tryGame = null;
		
		System.out.print("\nHere are the current games: ");
		for(int i = 0; i<games.length; i++) {
			System.out.print(games[i].getGameID() + " ");
		}
		
		do {
			System.out.println();
			System.out.print("Enter the game id: " );
			String response = keyboard.next();

			for(int i = 0; i < games.length && !done; i++) {
				if (games[i].getGameID().equals(response)) { 
					tryGame = games[i];
					System.out.println();
					System.out.println("You have selected " + tryGame.getWhichSport() + " race " + tryGame.getGameID() + ". ");
					done = true;							
				}
			}
			
			//	Later can create option to create new game		
			if (done == false) {
				//System.out.print("Would you like to create a new game (y/n)? ");
				System.out.println("I couldn't find that game.");
			}
		} while (!done);
		
		// Remove current user prediction
		setUserPrediction(null);
		
		return tryGame;
	}

	/**
	 * @return integer representing menu choice 1-6
	 */
	private int menuChoice() {
		System.out.println("Ozlympic Game");
		System.out.println("= = = = = = = = = = = = = = = = = =");
		System.out.println("1. Select a game to run");
		System.out.println("2. Predict the winner of the game");
		System.out.println("3. Start the game");
		System.out.println("4. Display the final results of all games");
		System.out.println("5. Display the points of all athletes");
		System.out.println("6. Exit");
		System.out.println();
//		System.out.print("Enter an option: ");
		
		int response =0;
		do {
			System.out.print("Enter an option: ");
			while (!keyboard.hasNextInt()) {
				keyboard.next();
				System.out.print("Enter an option: ");
			}
			response = keyboard.nextInt();
		} while (response < 1 || response > 6);
		return response;		
	}
	
	
	
	
	
	/**
	 * @return the games
	 */
	public Game [] getGames() {
		return games;
	}



	/**
	 * @param games the games to set
	 */
	public void setGames(Game [] games) {
		this.games = games;
	}

	/**
	 * @return the officials
	 */
	public Official [] getOfficials() {
		return officials;
	}

	/**
	 * @param officials the officials to set
	 */
	public void setOfficials(Official [] officials) {
		this.officials = officials;
	}

	/**
	 * @return the athletes
	 */
	public Athlete [] getAthletes() {
		return athletes;
	}

	/**
	 * @param athletes the athletes to set
	 */
	public void setAthletes(Athlete [] athletes) {
		this.athletes = athletes;
	}

	/**
	 * @return the userPrediction
	 */
	public Athlete getUserPrediction() {
		return userPrediction;
	}

	/**
	 * @param userPrediction the userPrediction to set
	 */
	public void setUserPrediction(Athlete userPrediction) {
		this.userPrediction = userPrediction;
	}


	/**
	 * @return the currentGame
	 */
	public Game getCurrentGame() {
		return currentGame;
	}


	/**
	 * @param currentGame the currentGame to set
	 */
	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

}
