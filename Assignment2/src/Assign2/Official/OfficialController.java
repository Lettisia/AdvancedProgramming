package Assign2.Official;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Assign2.Main;
import Assign2.Algorithm.GameFullException;
import Assign2.Algorithm.NoRefereeException;
import Assign2.Algorithm.NotEnoughAthletesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*
 * Controller class of -choose referee- pane
 * 
 * Author: Théo Dufort
 */

public class OfficialController implements Initializable {

	@FXML
	public ListView<String> RefereesView;

	ObservableList<String> athleteIDlist = FXCollections
			.observableArrayList(Main.getMyDriver().getRefereesNames(Main.getMyDriver().getOfficials()));

	/*
	 * Initializes RefereeView list
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RefereesView.setItems(athleteIDlist);

	}

	/*
	 * Handles click on "Choose referee" button
	 */
	public void buttonAction(ActionEvent event)
			throws IOException, GameFullException, NotEnoughAthletesException, NoRefereeException {
		List<Integer> nums = new ArrayList<Integer>();
		List<String> names = new ArrayList<String>();
		names = RefereesView.getSelectionModel().getSelectedItems();
		nums = RefereesView.getSelectionModel().getSelectedIndices();
		if (names.isEmpty())
			Main.showChooseRefereeAlert();
		else {
			Main.getMyDriver().selectOfficialwithInt(nums);
			Main.showGameRunningScene();
		}
	}

	/*
	 * Handles ENTER key pressed the same way as "Choose referee" button
	 */
	public void keyPressed(KeyEvent key)
			throws GameFullException, IOException, NotEnoughAthletesException, NoRefereeException {
		if (key.getCode() == KeyCode.ENTER) {
			List<Integer> nums = new ArrayList<Integer>();
			List<String> names = new ArrayList<String>();
			names = RefereesView.getSelectionModel().getSelectedItems();
			nums = RefereesView.getSelectionModel().getSelectedIndices();
			if (names.isEmpty())
				Main.showChooseRefereeAlert();
			else {
				Main.getMyDriver().selectOfficialwithInt(nums);
				Main.showGameRunningScene();
			}
		}
	}

}
