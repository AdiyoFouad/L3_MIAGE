package odjouoye.awouno.morpion.model;

import javafx.scene.control.Label;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Font;

public class TicTacToeSquare extends Label {

	private int row;
	private int column;

	private static TicTacToeModel model = TicTacToeModel.getInstance();

	private ObjectProperty<Owner> ownerProperty = new SimpleObjectProperty<>(Owner.NONE);
	private BooleanProperty winnerProperty = new SimpleBooleanProperty(false);

	public TicTacToeSquare(final int row, final int column) {
		this.row = row;
		this.column = column;
		
		this.getStyleClass().add("tictactoesquare");

		ownerProperty.bind(model.getSquare(row, column));
		winnerProperty.bind(model.getWinningSquare(row, column));

		textProperty().bind(Bindings.createStringBinding(() -> {
			Owner o = ownerProperty.get();
			return o == Owner.FIRST ? "X" : o == Owner.SECOND ? "O" : "";
		}, ownerProperty));

		this.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return winnerProperty.get() ? new Font("System", 30) : new Font("System", 20);
		}, winnerProperty));

	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
