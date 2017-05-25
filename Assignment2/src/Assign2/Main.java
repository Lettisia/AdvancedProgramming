package Assign2;

import java.io.IOException;
import java.util.Optional;

import Assign2.Algorithm.Driver;
import Assign2.Algorithm.Game;
import Assign2.Algorithm.NoRefereeException;
import Assign2.Algorithm.NotEnoughAthletesException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Main class, containing primary stage, main layout and main method to run the game
 * 
 * Author: Théo Dufort
 */
public class Main extends Application {

	private Stage primaryStage;
	private static BorderPane mainLayout;
	private static Driver myDriver = new Driver();

	/*
	 * Open primary stage and main menu at the start
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Ozlympics");

		showMainView();
		showMenu();

	}

	/*
	 * Shows Primary Stage
	 */
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/*
	 * Shows main menu pane
	 */
	public static void showMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Menu.fxml"));
		AnchorPane menu = loader.load();
		mainLayout.setCenter(menu);

	}

	/*
	 * Shows pane with choice of game types
	 */
	public static void showButton1Scene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Button1/Button1.fxml"));
		VBox menu1 = loader.load();
		mainLayout.setCenter(menu1);
	}

	/*
	 * Shows pane with results of all games
	 */
	public static void showButton4Scene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Button4/Button4.fxml"));
		VBox menu4 = loader.load();
		mainLayout.setCenter(menu4);
	}

	/*
	 * Shows pane with score of all athletes
	 */
	public static void showButton5Scene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Button5/Button5.fxml"));
		VBox menu5 = loader.load();
		mainLayout.setCenter(menu5);
	}

	/*
	 * Opens dialog box: Needs to select more athletes
	 */
	public static void showChooseEnoughAthletesAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Oops !");
		alert.setHeaderText("Choose at Least 5 Athletes");
		alert.setContentText("Press Control and Click on at Least 5 Athletes");
		alert.showAndWait();
	}

	/*
	 * Opens dialog box: Needs to select less athletes
	 */
	public static void showChooseLessAthletesAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Oops !");
		alert.setHeaderText("Choose 8 Athletes Maximum");
		alert.setContentText("You Chose too Many Athletes. Press Control and Unselect Some Athletes");
		alert.showAndWait();
	}

	/*
	 * Opens dialog box: Needs to select an Referee
	 */
	public static void showChooseRefereeAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Oops !");
		alert.setHeaderText("Choose a Referee !");
		alert.setContentText("Select a Referee and Click Ok");
		alert.showAndWait();
	}

	/*
	 * Shows dialog box to confirm player wants to exit application
	 */
	public static void showExitAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You are Leaving the Competition");
		alert.setContentText("Are you Sure Mate?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		} else { // Go back
		}
	}

	/*
	 * Shows pane with list of athletes for swimming event
	 */
	public static void showSwimmingScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Swimming/Swimming.fxml"));
		BorderPane swim = loader.load();
		mainLayout.setCenter(swim);
		Game game = new Game();
		game.setWhichSport("Swimming");
		myDriver.setCurrentGame(game);
	}

	/*
	 * Shows pane with list of athletes for running event
	 */
	public static void showRunningScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Running/Running.fxml"));
		BorderPane run = loader.load();
		mainLayout.setCenter(run);
		Game game = new Game();
		game.setWhichSport("Running");
		myDriver.setCurrentGame(game);
	}

	/*
	 * Shows pane with list of athletes for cycling event
	 */
	public static void showCyclingScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Cycling/Cycling.fxml"));
		BorderPane cycle = loader.load();
		mainLayout.setCenter(cycle);
		Game game = new Game();
		game.setWhichSport("Cycling");
		myDriver.setCurrentGame(game);
	}

	/*
	 * Shows pane to select from list of officials
	 */
	public static void showOfficialScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Official/Official.fxml"));
		BorderPane official = loader.load();
		mainLayout.setCenter(official);
	}

	/*
	 * Shows pane with results of game just played
	 */
	public static void showGameScene() throws IOException, NotEnoughAthletesException, NoRefereeException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Game/Game.fxml"));
		BorderPane game = loader.load();
		mainLayout.setCenter(game);
	}

	/*
	 * Shows pane where game takes place
	 */
	public static void showGameRunningScene() throws IOException, NotEnoughAthletesException, NoRefereeException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("GameRunning/GameRunning.fxml"));
		BorderPane game = loader.load();
		mainLayout.setCenter(game);
		getMyDriver().getCurrentGame().runGame();
		getMyDriver().addGameToList(getMyDriver().getCurrentGame());
	}

	/*
	 * Getters and setters
	 */
	public static Driver getMyDriver() {
		return myDriver;
	}

	public static void setMyDriver(Driver myDriver) {
		Main.myDriver = myDriver;
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
