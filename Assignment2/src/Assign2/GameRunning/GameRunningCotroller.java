package Assign2.GameRunning;

import java.io.IOException;

import Assign2.Main;
import Assign2.Algorithm.NoRefereeException;
import Assign2.Algorithm.NotEnoughAthletesException;
import javafx.event.ActionEvent;

/*
 * Controller class of -ongoing game- pane
 * 
 * Author: Théo Dufort
 */
public class GameRunningCotroller {
	public static String result = "try";

	/*
	 * Handles click on "Show Results" button
	 */
	public void buttonAction(ActionEvent event) throws IOException, NotEnoughAthletesException, NoRefereeException {
		result = Main.getMyDriver().getCurrentGame().printCompetitorResults();
		Main.showGameScene();
	}

}
