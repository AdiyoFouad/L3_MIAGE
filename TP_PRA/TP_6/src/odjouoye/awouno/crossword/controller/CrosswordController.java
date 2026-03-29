package odjouoye.awouno.crossword.controller;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import odjouoye.awouno.crossword.model.ChargeGrid;
import odjouoye.awouno.crossword.model.Clue;
import odjouoye.awouno.crossword.model.Crossword;
import odjouoye.awouno.crossword.model.CrosswordSquare;

public class CrosswordController {

	private int partie = 1;
	private ChargeGrid db = new ChargeGrid();
	private Crossword crossword;
	private CrosswordSquare currentSquare;

	private boolean horizontalDirection = true;
	private int currentCol;
	private int currentRow;

	@FXML
	GridPane crosswordGrid;

	@FXML
	ListView<Clue> horizontalList;
	
	@FXML
	ListView<Clue> verticalList;

	@FXML
	public void initialize() {

		loadGrid();

		Platform.runLater(() -> {
			crosswordGrid.getScene().setOnKeyPressed(ev -> handleKeyPress(ev));
			
			// Focus permanent sur grille pour que les touches directionnelles 
			// ne soit pas pris en compte au niveau des ListView
			crosswordGrid.requestFocus();
			crosswordGrid.focusedProperty().addListener((obs, old, newFocus) -> {
		        if (!newFocus) crosswordGrid.requestFocus();
		    });
		});
		
		horizontalList.setOnMouseClicked(e -> {
		    Clue clue = horizontalList.getSelectionModel().getSelectedItem();
		    if (clue != null) {
		        horizontalDirection = true;
		        selectCell(clue.getRow(), clue.getColumn());
		    }
		});
		verticalList.setOnMouseClicked(e -> {
		    Clue clue = verticalList.getSelectionModel().getSelectedItem();
		    if (clue != null) {
		        horizontalDirection = false;
		        selectCell(clue.getRow(), clue.getColumn());
		    }
		});

	}

	public void loadGrid() {
		crossword = db.extractGrid(partie);
		
		db.afficherApercu(crossword);

		for (int row = 0; row < crossword.getHeight(); row++) {
			for (int col = 0; col < crossword.getWidth(); col++) {
				CrosswordSquare square = crossword.getCell(row, col);
				configureSquare(square, row, col);
				crosswordGrid.add(square, col, row);
			}
		}
		
		horizontalList.setItems(crossword.getHorizontalClues());
		verticalList.setItems(crossword.getVerticalClues());
		

		// direction horizontal par défaut
    	horizontalList.getStyleClass().add("current-direction");
	}

	private void configureSquare(CrosswordSquare square, int row, int col) {
		square.getStyleClass().clear();
		square.getStyleClass().add("crossword-square");

		if (square.isBlack()) {
			square.getStyleClass().add("black-square");
			square.setMouseTransparent(true);
			return;
		}

		square.getStyleClass().add("white-square");

		square.setOnMouseClicked(e -> selectCell(row, col));

		square.textProperty().addListener((obs, oldText, newText) -> {
			if (newText != null && !newText.isBlank()) {
				playLetterAnimation(square);
			}
		});
	}

	private void selectCell(int row, int col) {
		if (crossword.isBlackSquare(row, col)) {
			return;
		}

		currentRow = row;
		currentCol = col;
		if (currentSquare != null) {
			currentSquare.getStyleClass().remove("current-square");
		}

		currentSquare = crossword.getCell(row, col);

		currentSquare.getStyleClass().add("current-square");
		
		selectIndice();
	}
	
	private void selectIndice() {

	    horizontalList.getSelectionModel().clearSelection();
	    verticalList.getSelectionModel().clearSelection();
	    
		Clue hClue = findHorizontalClue(currentRow, currentCol);
	    Clue vClue = findVerticalClue(currentRow, currentCol);
	    
	    
	    if (hClue != null) {
	    	horizontalList.getSelectionModel().select(hClue);
	    	horizontalList.scrollTo(hClue);
	    }
	    if (vClue != null) {
	    	verticalList.getSelectionModel().select(vClue);
	        verticalList.scrollTo(vClue);
	    }
	    
	    // On nettoie tout d'abord
	    horizontalList.getStyleClass().remove("current-direction");
	    verticalList.getStyleClass().remove("current-direction");
	    
	    if (horizontalDirection) {
	    	horizontalList.getStyleClass().add("current-direction");
	    } else {
	    	verticalList.getStyleClass().add("current-direction");
	    }
	}
	
	private Clue findHorizontalClue(int row, int col) {
	    int debutCol = col;
	    // le mot commence forcément à l'indice 0 ou juste après une case noire
	    while (debutCol > 0 && !crossword.isBlackSquare(row, debutCol - 1)) {
	        debutCol--;
	    }
	    
	    // Cherche l'indice qui commence exactement là
	    for (Clue clue : crossword.getHorizontalClues()) {
	        if (clue.getRow() == row && clue.getColumn() == debutCol) {
	            return clue;
	        }
	    }
	    return null;
	}

	private Clue findVerticalClue(int row, int col) {
	    int debutRow = row;
	    while (debutRow > 0 && !crossword.isBlackSquare(debutRow - 1, col)) {
	        debutRow--;
	    }
	    
	    // Cherche l'indice qui commence exactement là
	    for (Clue clue : crossword.getVerticalClues()) {
	        if (clue.getRow() == debutRow && clue.getColumn() == col) {
	            return clue;
	        }
	    }
	    return null;
	}

	private void playLetterAnimation(CrosswordSquare square) {
		ScaleTransition st = new ScaleTransition(Duration.millis(200), square);
		st.setFromX(0.3);
		st.setFromY(0.3);
		st.setToX(1.0);
		st.setToY(1.0);
		st.playFromStart();
	}

	private void handleKeyPress(KeyEvent event) {
		KeyCode code = event.getCode();
		switch (code) {
		case ENTER: {
			for (int r = 0; r < crossword.getHeight(); r++) {
				for (int c = 0; c < crossword.getWidth(); c++) {
					if (!crossword.isBlackSquare(r, c)) {
						CrosswordSquare square = crossword.getCell(r, c);
						char prop = square.getProposition();
						char sol = square.getSolution();

						if (prop != ' ' && prop == sol) {

							System.out.println("ok");
							if (!square.getStyleClass().contains("correct-square")) {
								square.getStyleClass().add("correct-square");
							}
						} else {
							square.getStyleClass().remove("correct-square");
						}
					}
				}
			}
			break;
		}
		case BACK_SPACE: {
			crossword.setProposition(currentRow, currentCol, ' ');
	        currentSquare.setText("");
	        moveBackward();
	        break;
		}
		case LEFT : {
			moveByArrow(true, false);
			break;
		}
		case RIGHT : {
			moveByArrow(true, true);
			break;
		}
		case UP : {
			moveByArrow(false, false);
			break;
		}
		case DOWN : {
			moveByArrow(false, true);
			break;
		}
		default: {
			if (event.isControlDown() && code == KeyCode.W) {
	            Platform.exit();
	            event.consume();
	            return;
	        }
			if (code.isLetterKey()) {
				if (crossword.isBlackSquare(currentRow, currentCol)) {
					return;
				}
				insertLetter(event.getText().toUpperCase().charAt(0));
			}
		}
		}

	}

	private void insertLetter(char letter) {
		if (currentSquare == null)
			return;
		crossword.setProposition(currentRow, currentCol, letter);
		currentSquare.setText(String.valueOf(letter));
		playLetterAnimation(currentSquare);
		moveForward();
	}

	private void moveForward() {
		// on déplace vers la gauche ou le bas
		move(horizontalDirection ? 0 : 1, horizontalDirection ? 1 : 0);
	}

	private void moveBackward() {
		// on déplace vers la droite ou le haut
		move(horizontalDirection ? 0 : -1, horizontalDirection ? -1 : 0);
	}

	private void move(int deltaRow, int deltaCol) {
		int newRow = currentRow + deltaRow;
		int newCol = currentCol + deltaCol;

		if (crossword.correctCoords(newRow, newCol) && !crossword.isBlackSquare(newRow, newCol)) {
			selectCell(newRow, newCol);
		}
	}

	private void moveByArrow(boolean newHorizontalDirection, boolean forward) {
		// Si la direction est différente de celle actuelle on change de direction
		// sans bouger le currentSquare
		if (horizontalDirection != newHorizontalDirection) {
			horizontalDirection = newHorizontalDirection;
			selectIndice();
			return;
		}
		if (forward) {
			moveForward();
		} else {
			moveBackward();
		}
	}

}
