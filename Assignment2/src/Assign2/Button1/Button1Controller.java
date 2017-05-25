package Assign2.Button1;

import java.io.IOException;
import java.util.Random;

import Assign2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/*
 * Controller class of -type of game choice- pane
 * 
 * Author: Théo Dufort
 */
public class Button1Controller {

	/*
	 * Following 3 methods handles a click on button corresponding to a type of
	 * game
	 */
	@FXML
	public void ClickSwimmingButton(ActionEvent event) throws IOException {
		Main.showSwimmingScene();
	}

	@FXML
	public void ClickRunningButton(ActionEvent event) throws IOException {
		Main.showRunningScene();

	}

	@FXML
	public void ClickCyclingButton(ActionEvent event) throws IOException {
		Main.showCyclingScene();

	}

	/*
	 * Handles "Random" button, Choose a type of game randomly
	 */
	@FXML
	public void ClickRandomButton(ActionEvent event) throws IOException {
		Random randomGenerator = new Random();
		int x = randomGenerator.nextInt(3) + 1;
		switch (x) {
		case 1:
			Main.showCyclingScene();
			break;
		case 2:
			Main.showRunningScene();
			break;
		case 3:
			Main.showSwimmingScene();
			break;
		}

	}

}
