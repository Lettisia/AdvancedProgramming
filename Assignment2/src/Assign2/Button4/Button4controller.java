package Assign2.Button4;

import java.net.URL;
import java.util.ResourceBundle;
import Assign2.view.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;

/*
 * Controller class of -summary of all games- scene
 * 
 * Author: Théo Dufort
 */
public class Button4controller implements Initializable {
	@FXML
	public Label results;

	/*
	 * Initialises results list
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		results.setText(MainController.resultss);
	}

	/*
	 * Handles scrolling up and down action
	 */
	public void scrollAction(ScrollEvent event) {
		if (results.heightProperty().getValue() > 264) {
			results.setTranslateY(results.getTranslateY() + event.getDeltaY());
			if (results.getTranslateY() > 0) {
				results.setTranslateY(0);
			}
			if (results.getTranslateY() < -results.heightProperty().getValue() + 264) {
				results.setTranslateY(-results.heightProperty().getValue() + 264);
			}
		}
	}

}
