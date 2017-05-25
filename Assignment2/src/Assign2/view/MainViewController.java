package Assign2.view;

import java.io.IOException;

import Assign2.Main;
import javafx.fxml.FXML;

/*
 * Controller class of primary stage
 * 
 * Author: Théo Dufort
 */
public class MainViewController {

	/*
	 * Handles "Main Menu" button
	 */
	@FXML
	private void goMenu() throws IOException {
		Main.showMenu();
	}

}
