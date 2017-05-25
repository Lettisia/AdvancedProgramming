package Assign2.Game;

import java.net.URL;
import java.util.ResourceBundle;

import Assign2.GameRunning.GameRunningCotroller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/*
 * controller class of game results pane
 * 
 * Author: Théo Dufort
 */
public class GameController implements Initializable {

	@FXML
	public Label text;

	/*
	 * Initializes text
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		text.setText(GameRunningCotroller.result);
	}

}
