package fr.istic.prg1.tp3;

/**
 * 
 * @author	Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr>
 * 			Fouad ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr> 
 * @version 1.0
 * @since 2025-10-01
 * 
 *        Classe représentant des doublets *non modifiables*
 */

public class Pair implements Comparable<Pair> {

	public Pair(int x, int y) {
	}

	@Override
	public int compareTo(Pair d) {
		return 0;
	}

	public Pair copyOf() {
		return null;
	}

	@Override
	public String toString() {
		return null;
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
		return false;
	}
}