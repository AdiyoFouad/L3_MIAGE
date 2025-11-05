package fr.istic.l3miage.prg.list;

import fr.istic.l3miage.prg.list_util.Iterator;
import fr.istic.l3miage.prg.list_util.SuperT;

/**
 * Liste en double chaÃ®nage par rÃ©fÃ©rences
 * 
 * @author MickaÃ«l Foursov
 * @author Vincent Drevelle
 * 
 *         Version corrigÃ©e et instrumentÃ©e (compte du nombre d'opÃ©rations).
 *         Utilise un versionnage pour empÃªcher les modifs concurrentes.
 * 
 * @param <T> : le type de valeurs stockÃ©es
 */
public class List<T extends SuperT<T>> {

	/**
	 * Element de la liste
	 */
	private class Element {
		private T value;
		private Element left;
		private Element right;

		public Element() {
			value = null;
			left = null;
			right = null;
		}
	}

	/**
	 * ItÃ©rateur sur la liste
	 */
	public class ListIterator implements Iterator<T> {

		/** L'Ã©lÃ©ment de la liste pointÃ© par l'itÃ©rateur */
		private Element current;

		/**
		 * Contructeur privÃ©. Place l'itÃ©rateur sur la tÃªte de la liste.
		 * L'itÃ©rateur doit Ãªtre construit par la liste
		 * 
		 * @see List::iterator()
		 */
		private ListIterator() {
		}

		@Override
		public void goForward() {
		}

		@Override
		public void goBackward() {
		}

		@Override
		public void restart() {
		}

		@Override
		public boolean isOnFlag() {
			return false;
		}

		@Override
		public void remove() {
			assert current != flag : "impossible de retirer le drapeau";
		}

		@Override
		public T getValue() {
			return null;
		}

		@Override
		public T nextValue() {
			return null;
		}

		@Override
		public void addLeft(T value) {
		}

		@Override
		public void addRight(T value) {
		}

		@Override
		public void setValue(T value) {
		}

		@Override
		public String toString() {
			return "Iterateur de liste : pas d'affichage possible \n";
		}

	} // class ListIterator

	/** Le drapeau (sentinelle) */
	private Element flag;

	/**
	 * Constructeur
	 * 
	 * Instancie une liste vide (contenant seulement le drapeau).
	 */
	public List() {
	}

	/**
	 * @return un itÃ©rateur sur la liste, lâ€™Ã©lÃ©ment courant de lâ€™itÃ©rateur est
	 *         positionnÃ© sur lâ€™Ã©lÃ©ment de tÃªte.
	 */
	public ListIterator iterator() {
		return null;
	}

	/**
	 * @return true si la liste est vide, false sinon
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Supprimer toutes les valeurs de la liste.
	 */
	public void clear() {
	}

	/**
	 * Affecter la valeur v au drapeau.
	 * 
	 * @param v valeur Ã  mettre dans le drapeau.
	 */
	public void setFlag(T v) {
	}

	/**
	 * Ajouter v en tÃªte de la liste.
	 * 
	 * @param v valeur Ã  ajouter
	 */
	public void addHead(T v) {
	}

	/**
	 * Ajouter v en queue de la liste.
	 * 
	 * @param v valeur Ã  ajouter
	 */
	public void addTail(T v) {
	}

	/**
	 * @return une copie profonde de la liste this.
	 */
	public List<T> copyOf() {
		List<T> nouvelleListe = new List<>();
		ListIterator it = iterator();
		while (!it.isOnFlag()) {
			nouvelleListe.addTail(it.getValue().copyOf());
			// UNE COPIE EST NECESSAIRE !!!
			it.goForward();
		}
		return nouvelleListe;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("contenu de la liste : \n");
		ListIterator it = iterator();
		while (!it.isOnFlag()) {
			result.append(it.getValue().toString());
			result.append(" ");
			it.goForward();
		}
		return result.toString();
	}

}