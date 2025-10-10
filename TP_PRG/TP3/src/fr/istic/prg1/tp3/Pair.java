package fr.istic.prg1.tp3;

/**
 * 
 * @author 	Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr> 
 * 			Fouad ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr>
 * @version 1.0
 * @since 2025-10-01
 * 
 *        Classe représentant des doublets *non modifiables*
 */

public class Pair implements Comparable<Pair> {

	private final int x;
	private final int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pair d) {
		if (x == d.x && y == d.y) {
			return 0;
		}
		if ((x == d.x && y > d.y) || x > d.x) {
			return 1;
		}
		return -1;
	}

	public Pair copyOf() {
		return new Pair(x, y);
	}

	@Override
	public String toString() {
		return "" + x + " " + y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		// DERNIER CAS À TRAITER
		Pair pairFromObj = (Pair)obj;
		return this.compareTo(pairFromObj) == 0;
	}
}