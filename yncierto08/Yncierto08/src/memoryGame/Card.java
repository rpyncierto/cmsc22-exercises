package memoryGame;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Card {

	private int value;

	private Image cardBackImg;
	private Image numberImg;

	private Boolean revealed = false;
	private Boolean canBeFlipped = true;
	private Boolean toFlip = true;

	private ImageView imgView;
	private MainGameStage game;



	/**
	 *
	 * @param game		A reference to the MainGameStage
	 * @param value		the Card's "hidden value" to be matched with one other Card. A number from 1-8
	 * @param width		The length of the side of the card (the card is square)
	 */

	public Card(MainGameStage game, int value, int length) {

		this.game = game;
		this.value = value;

		// TODO: Initialize Image View
		//  - Load up the Card back image
		this.cardBackImg = new Image("images/card_back.jpg", length, length, false, false);
		//  - Load up the image of the numbers
		switch(value) {
		case 0:
			this.numberImg = new Image("images/0.jpg", length, length, false, false);
			break;
		case 1:
			this.numberImg = new Image("images/1.jpg", length, length, false, false);
			break;
		case 2:
			this.numberImg = new Image("images/2.jpg", length, length, false, false);
			break;
		case 3:
			this.numberImg = new Image("images/3.jpg", length, length, false, false);
			break;
		case 4:
			this.numberImg = new Image("images/4.jpg", length, length, false, false);
			break;
		case 5:
			this.numberImg = new Image("images/5.jpg", length, length, false, false);
			break;
		case 6:
			this.numberImg = new Image("images/6.jpg", length, length, false, false);
			break;
		case 7:
			this.numberImg = new Image("images/7.jpg", length, length, false, false);
			break;
		case 8:
			this.numberImg = new Image("images/8.jpg", length, length, false, false);
			break;
	}
		//  - Setup layout properties and dimensions
		this.imgView = new ImageView();
		if(this.revealed == false) {
			this.imgView.setImage(this.cardBackImg);
		} else {
			this.imgView.setImage(this.numberImg);
		}
		this.imgView.setLayoutX(0);
		this.imgView.setLayoutY(0);
		this.imgView.setPreserveRatio(true);
		this.imgView.setFitWidth(length);
		this.imgView.setFitHeight(length);

		this.setHandler();
	}

	private void setHandler() {
		Card card = this;

		this.imgView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				card.flip();
			}
		});
	}

	public void flip() {


		// check if the card can be flipped
		if (!canBeFlipped || !game.isFlippingAllowed()) { return; }


		// Check game state. Checking happens in MainGameStage.java
		game.checkState(this);

		if(toFlip) { // flip the card
			if (this.revealed) {
				flipDown();
			} else {
				flipUp();
			}
		}
		this.toFlip = true;
	}

	// flips the card into its back
	public void flipDown() {
		// check if the card can be flipped
		if (!canBeFlipped) { return; }
		this.imgView.setImage(this.cardBackImg);
		this.revealed = false;
	}

	// flips the card into its front
	public void flipUp() {
		// check if the card can be flipped
		if (!canBeFlipped) { return; }
		this.imgView.setImage(this.numberImg);
		this.revealed = true;
	}

	public ImageView getImageView() {
		return imgView;
	}

	public int getValue() {
		return value;
	}

	public boolean isRevealed() {
		return revealed;
    }


	public void setFlip(boolean b) {
		this.toFlip = b;
	}

}
