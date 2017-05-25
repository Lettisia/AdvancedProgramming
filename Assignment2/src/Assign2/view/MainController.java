package Assign2.view;

import java.io.IOException;
import Assign2.Main;
import javafx.fxml.FXML;

/*
 * Controller class of menu
 * 
 * Author: Théo Dufort
 * 
 */
public class MainController {
	public static String resultss = "try";
	public static String scoress = "try";

	/*
	 * Handlers of different menu options
	 */

	@FXML
	private void goButton1() throws IOException {
		Main.showButton1Scene();
	}

	@FXML
	private void goButton4() throws IOException {
		resultss = Main.getMyDriver().displayResults();
		Main.showButton4Scene();
	}

	@FXML
	private void goButton5() throws IOException {
		scoress = Main.getMyDriver().printPlayerPoints();
		Main.showButton5Scene();
	}

	@FXML
	private void goButton6() throws IOException {
		Main.showExitAlert();
	}

}
