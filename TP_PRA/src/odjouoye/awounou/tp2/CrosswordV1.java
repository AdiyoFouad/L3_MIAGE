package odjouoye.awounou.tp2;

/**
* Variables d’instance :
* solution
* proposition
* horizontal
* vertical
*/

public class CrosswordV1<T> {
	/**
	* Constructeur créant une instance de Crossword
	* dotée de 4 instances de Grid, suivant les
	* spécifications données ci-dessous
	*/
	
	private Grid<T> solution;
	private Grid<T> proposition;
	private Grid<T> horizontal;
	private Grid<T>  vertical;
	
	public CrosswordV1 (int height, int width) {
		solution = new Grid<>(height, width);
		proposition = new Grid<>(height, width);
		horizontal = new Grid<>(height, width);
		vertical = new Grid<>(height, width);
	}
	
	/**
	* @return nombre de rangées
	*/
	public int getHeight() {
		return this.solution.getHeight();
	}
	
	/**
	* @return nombre de colonnes
	*/
	public int getWidth() {
		return this.solution.getWidth();
	}
	
	/**
	* Vérifier la validité des coordonnées
	* @return true si et seulement si (row, column)
	* désignent une cellule existante de la grille
	*/
	public boolean correctCoords(int row, int column) {
		return this.solution.correctCoords(row, column);
	}

	/**
	* @return true si la case est noire, false sinon
	* @pre correctCoords(row, column)
	*/
	public boolean isBlackSquare(int row, int column) {
		return this.solution.getCell(row, column) == null;
	}
	
	/**
	* Déclarer la case (row, column) noire ou blanche
	*/
	public void setBlackSquare(int row, int column, boolean black){
		if (black) {
			this.solution.setCell(row, column, null);
		} else {
			this.solution.setCell(row, column, (T)" ");
		}
	}
	
	/**
	* @return la solution dans la case (row, column)
	* @pre correctCoords(row, column) && !isBlackSquare(row, column)
	*/
	public T getSolution(int row, int column) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		return this.solution.getCell(row, column);
	}

	public void setSolution(int row, int column, T solution) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		this.solution.setCell(row, column, solution);
	}
	
	public T getProposition(int row, int column) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		return this.proposition.getCell(row, column);
	}
	
	public void setProposition(int row, int column, T prop) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		this.proposition.setCell(row, column, prop);
	}
	
	/**
	* @return la définition horizontale dans (row, column)
	* si horizontal, et la définition verticale sinon
	* @pre correctCoords(row, column) && !isBlackSquare(row, column)
	*/
	public T getDefinition(int row, int column,boolean horizontal) {
		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		if (horizontal) {
			return this.horizontal.getCell(row, column);
		}
		return this.vertical.getCell(row, column);
	}
	
	public void setDefinition(int row, int column,boolean horizontal, T definition) {

		if (isBlackSquare(row, column)) {
			throw new RuntimeException();
		}
		if (horizontal) {
			this.horizontal.setCell(row, column, definition);
			return;
		}
		this.vertical.setCell(row, column, definition);
	}




}
