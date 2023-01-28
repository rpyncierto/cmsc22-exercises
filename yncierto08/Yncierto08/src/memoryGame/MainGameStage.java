package memoryGame;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainGameStage {
	private Scene scene;
	private Stage stage;

	private Group root;
	private Canvas scoreCanvas;
	private GridPane cardGrid = new GridPane();

	// The window is square so height is also 500
	public final static int WINDOW_WIDTH = 500;
	public final static int WINDOW_PADDING = 50;

	// the grid is square to the columns are also 4
	private final static int CARD_ROWS = 4;
	private final static int CARD_PADDING = 5;

	private Boolean startedPairOpening = false;
	private Boolean flippingAllowed = true;

	private Card[] openedCards = new Card[2];
	private int totalMatchedPairs = 0;
	private int moveCount = 0;

	private ArrayList<Card> cards;
	private ArrayList<Card> matchedCards = new ArrayList<Card>();

	public final Image bg = new Image("images/background.jpg", WINDOW_WIDTH, WINDOW_WIDTH, false, false);

	public MainGameStage(Stage stage) {

		this.stage = stage;

		this.root = new Group();

		// TODO: Set background image Canvas
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_WIDTH);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(bg, 0, 0);
		this.root.getChildren().add(canvas);

		// initializes the game elements (score display, cards)
		initGameElements();

		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_WIDTH, Color.WHITE);

		this.stage.setTitle("Memory Game");
		this.stage.setScene(this.scene);
		this.stage.show();

	}

	// Adds the score Canvas and card GridPane
	public void initGameElements() {

		// TODO: Create score canvas and add it to the root group
		scoreCanvas = new Canvas(WINDOW_WIDTH, WINDOW_PADDING);
		GraphicsContext gc = scoreCanvas.getGraphicsContext2D();
		gc.setFill(Color.TRANSPARENT);
		gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_PADDING);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		gc.fillText("Moves: " + moveCount, 5, 25);
		root.getChildren().add(scoreCanvas);

		// Sets up the Card GridPane and add it to the root group
		initCards();
		root.getChildren().add(cardGrid);

	}

	private void initCards(){

		// Initalizes the 16 Cards, randomize their values, and it to the grid
		this.addCardsToGrid();

		// Sets the layout properties of the 4x4 card grid
		this.setGridLayoutProperties();
	}

	private void addCardsToGrid() {

		// calculates card length (square) based on window size, padding, number of cards in grid, and padding in between
		int cardLength = ((WINDOW_WIDTH - (WINDOW_PADDING * 2)) - ((CARD_ROWS - 1) * CARD_PADDING)) / CARD_ROWS;


		// TODO: Instantiate 16 cards with random values from 1-8, distributed among them twice. Add these cards to the gridPane property
		ArrayList<Integer> cardValues = new ArrayList<Integer>();
		for (int i = 1; i <= 8; i++) {
			cardValues.add(i);
			cardValues.add(i);
		}
		Collections.shuffle(cardValues);
		this.cards = new ArrayList<Card>();
		for (int i = 0; i < 16; i++) {
			Card card = new Card(this, cardValues.get(i), cardLength);
			this.cards.add(card);
		}
	}

	// Layout properties such as padding around the grid, between the cards, etc.
	private void setGridLayoutProperties(){
		// TODO: Set layout properties. Use the WINDOW_WITH, WINDOW_PADDING, CARD_WIDTH, CARD_PADDING properties
		int cardLength = ((WINDOW_WIDTH - (WINDOW_PADDING * 2)) - ((CARD_ROWS - 1) * CARD_PADDING)) / CARD_ROWS;
		this.cardGrid.setPrefSize(cardLength * 4 + CARD_PADDING * 3, cardLength * 4 + CARD_PADDING * 3);
		this.cardGrid.setLayoutX(WINDOW_WIDTH*0.1);
		this.cardGrid.setLayoutY(WINDOW_WIDTH*0.1);
		this.cardGrid.setVgap(CARD_PADDING);
		this.cardGrid.setHgap(CARD_PADDING);

		int count = 0;
		for(int row = 0; row < CARD_ROWS; row++){
			for(int col = 0; col < CARD_ROWS; col++){
				GridPane.setConstraints(this.cards.get(count).getImageView(),col,row);
				count++;
			}
		}

		for(Card card : cards){
			this.cardGrid.getChildren().add(card.getImageView());
		}
	}


	private void updateScore() {
		// TODO: Clear the score canvas and display an updated one
		GraphicsContext gc = scoreCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_PADDING);
		gc.setFill(Color.TRANSPARENT);
		gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_PADDING);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		gc.fillText("Moves: " + moveCount, 5, 25);

	}


	void checkState(Card card) {

		// TODO: Disable flipping face down of the first card until a second card is revealed

		if (matchedCards.contains(card)) { // if the card is already in the match cards array
			card.setFlip(false);
			return;
		}


		// this takes note of the first opened card
		if (!startedPairOpening) {
			openedCards[0] = card;
		}

		// this takes note of the second opened card
		else {
			if (openedCards[0] == card && openedCards[0].isRevealed() == card.isRevealed()) { // if the first and second card is the same
				card.setFlip(false);
				return;
			}
			openedCards[1] = card;

			// TODO: Update move count after every 2 cards opened
			moveCount++;
			updateScore();

			// TODO: Disable flipping cards globally while game state is checked
			flippingAllowed = false;

			// TODO: If the cards match, no need to hide them again. Allow flipping cards again immediately
			if (openedCards[0].getValue() == openedCards[1].getValue()) {
				// add cards to the array or matched cards
				matchedCards.add(openedCards[0]);
				matchedCards.add(openedCards[1]);
				flippingAllowed = true;
				totalMatchedPairs++;
				// TODO: Check if all cards have been matched. If yes, display game over scene, else cotninue the game
				if (totalMatchedPairs == 8) {
					displayGameOverScene();
					return;
				}
			}

			// TODO: If the cards don't match. Hide them after a 1 second delay. Do not allow flipping of other cards during the delay
			else {

				// TODO: After the delay, flip down the 2 revealed cards. Enable flipping again after the delay
				PauseTransition delay = new PauseTransition(Duration.seconds(1));
				delay.setOnFinished(event -> {
					openedCards[0].flipDown();
					openedCards[1].flipDown();
					flippingAllowed = true;
				});
				delay.play();
			}
		}


		// This is for keeping track of the cards opened
		startedPairOpening = !startedPairOpening;
	}

	Boolean isFlippingAllowed() {
		return flippingAllowed;
	}


	private void displayGameOverScene(){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();

		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				GameOverStage gameover = new GameOverStage(moveCount, WINDOW_WIDTH);
				stage.setScene(gameover.getScene());
			}
		});
	}

	// check if card is already in the match cards array
	public boolean isMatchCard(Card card) {
		for (Card c : matchedCards) {
			if (c == card) {
				return true;
			}
		}
		return false;
	}
}

