package odjouoye.awounou.tp2;

public class CrosswordSquare {

	
	private char solution;
	private char proposition;
	private String horizontal;
	private String vertical;
	private boolean black;
//	
//	public CrosswordSquare() {
//		solution = ' ';
//		proposition = ' ';
//		horizontal = "";
//		vertical = "";
//		black = false;
//		
//	}
	
	public char getSolution() {
		return solution;
	}
	public void setSolution(char solution) {
		this.solution = solution;
	}
	public char getProposition() {
		return proposition;
	}
	public void setProposition(char proposition) {
		this.proposition = proposition;
	}
	public String getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(String horizontal) {
		this.horizontal = horizontal;
	}
	public String getVertical() {
		return vertical;
	}
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	public boolean isBlack() {
		return black;
	}
	public void setBlack(boolean black) {
		this.black = black;
	}
	
	
	
	
}
