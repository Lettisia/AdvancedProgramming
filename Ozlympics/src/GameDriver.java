import java.util.Scanner;

/**
 * 
 * Assumption:
 * - the user can only predict the result of the current game.
 * 
 * @author lettisia
 *
 */

public class GameDriver {
	
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
	}

	
	// Method for menu system and game play
	public void startMenu() {
		int choice;
		do{
			choice = menuChoice();
			
			if (choice == 1) {
				currentGame = chooseGame();
			}
			else if (choice == 2) {
				setUserPrediction(predictWinner());
			}
			else if (choice == 3) {
				startGame();
			}
			else if (choice == 4) {
				displayResults();
			}
			else if (choice == 5) {
				displayPoints();
			}
		} while (choice != 6);
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * Asks the user to select one of the athletes in the current game
	 * @return the Athlete predicted to win by the user
	 */
	private Athlete predictWinner() {
		Athlete newWinner = null;
		boolean done = false;
		Scanner keyboard = new Scanner(System.in);
		
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
			
			int response = keyboard.nextInt();
			response--;
			
			if (response >= 0 && response < currentGame.numAthletes()) {
				newWinner = currentGame.getAthletes()[response];
				done = true;
			}
			
			if (done == false) {
				System.out.println("I couldn't find that Athlete.");
			}

		} while (!done);
		
		keyboard.close();
		return newWinner;
	}

	private Game chooseGame() {
		boolean done = false;
		Scanner keyboard = new Scanner(System.in);
		Game tryGame = null;
		
		System.out.println("Here are the current games: ");
		for(int i = 0; i<games.length; i++) {
			System.out.print(games[i].getGameID() + " ");
		}
		
		do {
			System.out.println("");
			System.out.print("Enter the game id:" );
			String response = keyboard.nextLine();
			
			for(int i = 0; i < games.length && !done; i++) {
				if (games[i].getGameID() == response) { 
					tryGame = games[i];
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
		
		keyboard.close();
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
		System.out.println("");
		System.out.print("Enter an option: ");
		
		Scanner keyboard = new Scanner(System.in);
		int response;
		do {
			response = keyboard.nextInt();
		} while (response < 1 || response > 6);
		keyboard.close();
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

}
