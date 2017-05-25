package Assign2.Running;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Assign2.Main;
import Assign2.Algorithm.Game;
import Assign2.Algorithm.GameFullException;
import Assign2.Algorithm.NoRefereeException;
import Assign2.Algorithm.NotEnoughAthletesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*
 * Controller class of running events scene
 * 
 * Author: Théo DUFORT
 */
public class RunningController implements Initializable {

	@FXML
	public ListView<String> AthletesView;

	ObservableList<String> athleteIDlist = FXCollections
			.observableArrayList(Main.getMyDriver().getAthletesNames(Main.getMyDriver().getRunners()));

	/*
	 * Initializes AthletesView
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AthletesView.setItems(athleteIDlist);
		AthletesView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	/*
	 * Handles click on "Choose Athletes" button
	 */
	public void buttonAction(ActionEvent event) throws IOException, GameFullException {
		List<Integer> nums = new ArrayList<Integer>();
		List<String> names = new ArrayList<String>();
		names = AthletesView.getSelectionModel().getSelectedItems();
		nums = AthletesView.getSelectionModel().getSelectedIndices();
		if (names.size() < Game.getMinCompetitors())
			Main.showChooseEnoughAthletesAlert();
		else if (names.size() > Game.getMaxCompetitors())
			Main.showChooseLessAthletesAlert();
		else {
			Main.getMyDriver().selectAthwithInt(Main.getMyDriver().getRunners(), nums);
			Main.showOfficialScene();
		}
	}

	/*
	 * Handles ENTER key pressed the same way as "Choose Athletes" button
	 */
	public void keyPressed(KeyEvent key)
			throws GameFullException, IOException, NotEnoughAthletesException, NoRefereeException {
		if (key.getCode() == KeyCode.ENTER) {
			List<Integer> nums = new ArrayList<Integer>();
			List<String> names = new ArrayList<String>();
			names = AthletesView.getSelectionModel().getSelectedItems();
			nums = AthletesView.getSelectionModel().getSelectedIndices();
			if (names.size() < Game.getMinCompetitors())
				Main.showChooseEnoughAthletesAlert();
			else if (names.size() > Game.getMaxCompetitors())
				Main.showChooseLessAthletesAlert();
			else {
				Main.getMyDriver().selectAthwithInt(Main.getMyDriver().getRunners(), nums);
				Main.showOfficialScene();
			}
		}
	}

}
