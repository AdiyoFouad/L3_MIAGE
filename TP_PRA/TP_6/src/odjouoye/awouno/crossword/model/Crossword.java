package odjouoye.awouno.crossword.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Crossword extends Grid<CrosswordSquare>{
	/**
	* Constructeur créant une instance de Crossword
	* dotée de 4 instances de Grid, suivant les
	* spécifications données ci-dessous
	*/

	private ObservableList<Clue> verticalClues;
	private ObservableList<Clue> horizontalClues;

	
	public Crossword (int height, int width) {
		super(height,width);
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.setCell(i, j,  new CrosswordSquare());
            }
        }
	    horizontalClues = FXCollections.observableArrayList();
	    verticalClues = FXCollections.observableArrayList();
	}
	

	public StringProperty propositionProperty(int row, int column) {
		return new SimpleStringProperty(String.valueOf(getProposition(row, column)));
	}
	
	/**
	* @return true si la case est noire, false sinon
	* @pre correctCoords(row, column)
	*/
	public boolean isBlackSquare(int row, int column) {
		return this.getCell(row, column).isBlack();
	}
	
	/**
	* Déclarer la case (row, column) noire ou blanche
	*/
	public void setBlackSquare(int row, int column, boolean black){
		//System.out.println(this.getCell(row, column));
		this.getCell(row, column).setBlack(black);
	}
	
	/**
	* @return la solution dans la case (row, column)
	* @pre correctCoords(row, column) && !isBlackSquare(row, column)
	*/
	public char getSolution(int row, int column) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		return this.getCell(row, column).getSolution();
	}

	public void setSolution(int row, int column, char solution) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		this.getCell(row, column).setSolution(solution);
	}
	
	public char getProposition(int row, int column) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		return this.getCell(row, column).getProposition();
	}
	
	public void setProposition(int row, int column, char prop) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		this.getCell(row, column).setProposition(prop);
	}
	
	/**
	* @return la définition horizontale dans (row, column)
	* si horizontal, et la définition verticale sinon
	* @pre correctCoords(row, column) && !isBlackSquare(row, column)
	*/
	public String getDefinition(int row, int column,boolean horizontal) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		if (horizontal) {
			return this.getCell(row, column).getHorizontal();
		}
		return this.getCell(row, column).getVertical();
	}
	
	public void setDefinition(int row, int column,boolean horizontal, String definition) {

		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		if (horizontal) {
			this.getCell(row, column).setHorizontal(definition);
			return;
		}
		this.getCell(row, column).setVertical(definition);
	}



	public ObservableList<Clue> getVerticalClues() {
		return this.verticalClues;
	}
	
	public ObservableList<Clue> getHorizontalClues(){
		return this.horizontalClues;
	}
	
	public void printProposition() {
		
	}
	
	public void printSolution() {
		
	}
	
	private void generateClues() {
	    horizontalClues = FXCollections.observableArrayList();
	    verticalClues = FXCollections.observableArrayList();
	    
	    // Génère les indices depuis les définitions des cases
	    for (int i = 0; i < getHeight(); i++) {
	        for (int j = 0; j < getWidth(); j++) {
	            CrosswordSquare square = getCell(i, j);
	            if (!square.isBlack()) {
	                if (square.getHorizontal() != null && !square.getHorizontal().trim().isEmpty()) {
	                    horizontalClues.add(new Clue(square.getHorizontal(), i, j, true));
	                }
	                if (square.getVertical() != null && !square.getVertical().trim().isEmpty()) {
	                    verticalClues.add(new Clue(square.getVertical(), i, j, false));
	                }
	            }
	        }
	    }
	}


}
