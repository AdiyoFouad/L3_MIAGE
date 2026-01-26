package odjouoye.awounou.tp1;


/**
3 * Variables d’instance
4 * height = nombre de lignes
5 * width = nombre de colonnes
6 * array = tableau de chaînes de caractères à deux dimensions,
7 * avec taille = height x width
8 */

public class Grid {
	
	private int height;
	private int width;
	private String[][] array;
	

	/**
	* Constructeur permettant d’obtenir une grille dotée d’un tableau
	* de dimensions conformes aux valeurs respectives de height et
	* de width, dont tous les éléments contiennent la valeur null.
	* @pre height >= 0 et width >= 0
	*/
	public Grid (int height, int width) {

		if (height>=0 && width>= 0) {
			this.height = height;
			this.width = width;
			this.array = new String[height][width];
		} else {
			throw new IllegalArgumentException("Une des dimensions est négative");
		}
		
	}
	
	/**
	* Vérifier la validité des coordonnées
	* @return true si et seulement si (row, column)
	* désignent une cellule existante de la grille
	*/
	public boolean correctCoords(int row, int column) {
		return (0 <= row && row < height && 0 <= column && column < width); 
	}
	
	/**
	* @return valeur de la cellule ayant pour coordonnées
	* (row, column), où (row, column) est compris entre 0 et
	* getHeight()-1 (resp. getWidth()-1)
	* @pre correctCoords(row, column)
	*/
	
	public String getCell(int row, int column) {
		if (!correctCoords(row, column)) {
			throw new RuntimeException("Coordonnées incorrectes!");
		}
		return this.array[row][column];
	}

	/**
	* Modifier de la cellule de rangée row et colonne column
	* @pre coordCorrectes(row, column)
	*/
	public void setCell(int row, int column, String string) {
		if (!correctCoords(row, column)) {
			throw new RuntimeException("Coordonnées incorrectes!");
		}
		this.array[row][column] = string;
	}
	
	/**
	* @return texte sur height lignes ; colonnes séparées par des
	*/
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				s.append(array[i][j]);
				if(j != width -1) {
					s.append("|");
				}
			}
			s.append("\n");
		}
		
		return s.toString();	
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public String[][] getArray() {
		return array;
	}

	public void setArray(String[][] array) {
		this.array = array;
	}
	
	
	public static void main(String[] args) {
		Grid maGrille = new Grid(3,5) ;
		for (int l = 0; l < maGrille.getHeight(); l++) {
			String lineNumber = Integer.toString(l);
			for (int c = 0; c < maGrille.getWidth(); c++) {
				maGrille.setCell(l, c,lineNumber + "," + Integer.toString(c));
			}
		}
		System.out.println(maGrille) ;

	}

}
