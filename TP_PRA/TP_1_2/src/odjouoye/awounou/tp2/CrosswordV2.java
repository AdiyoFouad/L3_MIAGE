package odjouoye.awounou.tp2;


public class CrosswordV2 extends Grid<CrosswordSquare>{
	/**
	* Constructeur créant une instance de Crossword
	* dotée de 4 instances de Grid, suivant les
	* spécifications données ci-dessous
	*/


	
	public CrosswordV2 (int height, int width) {
		super(height,width);
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.setCell(i, j,  new CrosswordSquare());
            }
        }
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
			this.getCell(row, column).setHorizontal(definition);;
			return;
		}
		this.getCell(row, column).setVertical(definition);;
	}





}
