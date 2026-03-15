package odjouoye.awouno.morpion.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import odjouoye.awouno.morpion.model.Owner;
import odjouoye.awouno.morpion.model.TicTacToeModel;
import odjouoye.awouno.morpion.model.TicTacToeSquare;

public class Controller {

	private final int SIZE = 3;
	private static TicTacToeModel model = TicTacToeModel.getInstance();

	@FXML
	private GridPane morpion;

	@FXML
	private Label labelX;

	@FXML
	private Label labelO;

	@FXML
	private Label labelFree;

	@FXML
	private Label gameOverLabel;

	@FXML
	public void initialize() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				TicTacToeSquare square = new TicTacToeSquare(i, j);
				morpion.add(square, i, j);
				square.setOnMouseClicked(e -> handleClicked(square));
				square.setOnMouseEntered(e -> handleEntered(square));
				square.setOnMouseExited(e -> handleExited(square));
			}
		}

		gameOverLabel.textProperty().bind(model.getEndOfGameMessage());

		labelX.textProperty().bind(Bindings.createStringBinding(() -> {
			return calculNbreCase(Owner.FIRST) + " pour X";
		}, model.nbreCoupsProperty()));

		labelO.textProperty().bind(Bindings.createStringBinding(() -> {
			return calculNbreCase(Owner.SECOND) + " pour O";
		}, model.nbreCoupsProperty()));

		labelFree.textProperty().bind(Bindings.createStringBinding(() -> {
			return calculNbreCase(Owner.NONE);
		}, model.nbreCoupsProperty()));

		labelX.styleProperty().bind(Bindings.createStringBinding(() -> {
			return model.turnProperty().get() == Owner.FIRST && !model.gameOver().get() ? "-fx-background-color: cyan;"
					: "-fx-background-color: #FF6666;";
		}, model.nbreCoupsProperty()));

		labelO.styleProperty().bind(Bindings.createStringBinding(() -> {
			return model.turnProperty().get() == Owner.SECOND && !model.gameOver().get() ? "-fx-background-color: cyan;"
					: "-fx-background-color: #FF6666;";
		}, model.nbreCoupsProperty()));

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

	private String calculNbreCase(Owner o) {
		int c = model.nbreCoupsProperty().get();

		if (o == Owner.FIRST) {
			int nbreX = c <= 1 ? c : (c + 1) / 2;
			return nbreX > 1 ? nbreX + " cases" : nbreX + " case";
		} else if (o == Owner.SECOND) {
			int nbreO = c / 2;
			return nbreO > 1 ? nbreO + " cases" : nbreO + " case";
		}
		// Case Libre
		if (model.gameOver().get()) {
			//System.out.println("ggg");
			return "";
		} else {
			return SIZE * SIZE - c + (SIZE * SIZE - c > 1 ? " cases libres" : " case libre");
		}

	}

	public void restartGame() {
		model.restart();
	}
}
