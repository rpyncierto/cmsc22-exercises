package memoryGame;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverStage {
	private Scene scene;
	private StackPane stackPane;

	GameOverStage(int moveCount, int windowWidth){

		Canvas canvas = new Canvas(windowWidth, windowWidth);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// TODO: Add background to the canvas
		Image bg = new Image("images/background.jpg", windowWidth, windowWidth, false, false);
		gc.drawImage(bg, 0, 0);


		// TODO: Add the move count message to the canvas
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		gc.fillText("You finished the board in " + moveCount + " moves!", 15, 180);


		// TODO: Create an exit button
		Button exitButton = new Button("Exit");
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});

		// TODO: Create Stack pane and add the text and button to it
		this.stackPane = new StackPane();
		this.stackPane.getChildren().addAll(canvas, exitButton);


		this.scene = new Scene(stackPane, windowWidth, windowWidth);
	}


	Scene getScene(){
		return this.scene;
	}
}
