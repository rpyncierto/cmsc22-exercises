/*******************************************************************************************************************************************
 * This is an implementation of a memory game in which the cards are initially face down (with the same background) and the user selects   *
 * two cards. If the two cards have the same value, they will remain face up; otherwise, they will be turned face down. This will continue *
 * until all cards have been matched to their corresponding pairs.                                                                         *
 *                                                                                                                                         *
 *                                                                                                                                         *
 * @author Reymond P. Yncierto																											   *
 * @created_date 2022-11-24 7:38																										   *
 * @finished_date 2022-11-26 14:31																									       *
 *																																		   *
 *******************************************************************************************************************************************/

package memoryGame;

import javafx.application.Application;
import javafx.stage.Stage;

public class MemoryGame extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		@SuppressWarnings("unused")
		MainGameStage gameStage = new MainGameStage(stage);
	}

}
