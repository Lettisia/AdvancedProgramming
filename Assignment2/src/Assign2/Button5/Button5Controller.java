package Assign2.Button5;

import java.net.URL;
import java.util.ResourceBundle;

import Assign2.view.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;

/*
 * Controller class of -show all athletes scores- pane
 * 
 * Author: Théo DUFORT
 */

public class Button5Controller implements Initializable {
	@FXML
	public Label scores;

	/*
	 * Initializes scores list
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scores.setText(MainController.scoress);
	}

	/*
	 * Handles scrolling up and down action
	 */
	public void scrollAction(ScrollEvent event) {
		if (scores.heightProperty().getValue() > 264) {
			scores.setTranslateY(scores.getTranslateY() + event.getDeltaY());
			if (scores.getTranslateY() > 0) {
				scores.setTranslateY(0);
			}
			if (scores.getTranslateY() < -scores.heightProperty().getValue() + 264) {
				scores.setTranslateY(-scores.heightProperty().getValue() + 264);
			}
		}
	}

}
