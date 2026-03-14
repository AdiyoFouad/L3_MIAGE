package odjouoye.awouno.morpion.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import odjouoye.awouno.morpion.model.TicTacToeModel;
import odjouoye.awouno.morpion.model.TicTacToeSquare;

public class Controller {
	
	private final int SIZE = 3;
	private static TicTacToeModel model = TicTacToeModel.getInstance();

	@FXML
	private GridPane root;

	@FXML
	public void initialize() {
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				TicTacToeSquare square = new TicTacToeSquare(i, j);
				root.add(square, i, j);
				square.setOnMouseClicked(e -> handleClicked(square));
				square.setOnMouseEntered(e -> handleEntered(square));
				square.setOnMouseExited(e -> handleExited(square));
			}
		}
		
	}

	private void handleClicked(TicTacToeSquare square) {
		model.play(square.getRow(), square.getColumn());
	}

	private void handleEntered(TicTacToeSquare square) {
		if (model.legalMove(square.getRow(), square.getColumn()).get()) {
			square.getStyleClass().add("hover-valid");
		} else {

			square.getStyleClass().add("hover-invalid");
		}
	}

	private void handleExited(TicTacToeSquare square) {
		square.getStyleClass().removeAll("hover-valid", "hover-invalid");
	}
}
