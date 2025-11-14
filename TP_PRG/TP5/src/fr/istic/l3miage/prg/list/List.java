package fr.istic.l3miage.prg.list;

import fr.istic.l3miage.prg.list_util.Iterator;
import fr.istic.l3miage.prg.list_util.SuperT;

/**
 * Liste en double chaÃ®nage par rÃ©fÃ©rences
 * 
 * @author ODJOUOYE Fouad <fouad.odjouoye@etudiant.univ-rennes.fr>
 * @version 1.0
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
			this.current = List.this.flag.right;	
		}

		@Override
		public void goForward() {
			current = current.right; 
		}

		@Override
		public void goBackward() {
			current = current.left;
		}

		@Override
		public void restart() {
			current = List.this.flag.right;
		}

		@Override
		public boolean isOnFlag() {
			return current == List.this.flag;
		}

		@Override
		public void remove() {
			assert current != flag : "impossible de retirer le drapeau";
			current.left.right = current.right;
			current.right.left = current.left;
			current = current.right;
		}

		@Override
		public T getValue() {
			return current.value;
		}

		@Override
		public T nextValue() {
			return current.right.value;
		}

		@Override
		public void addLeft(T value) {
			Element newElement = new Element();
			
			newElement.value = value;
			newElement.left = current.left;
			newElement.right = current;
			
			current.left.right = newElement;
			current.left = newElement;
			
			current = newElement;
			
		}

		@Override
		public void addRight(T value) {
			Element newElement = new Element();
			
			newElement.value = value;
			newElement.left = current;
			newElement.right = current.right;
			
			current.right.left = newElement;
			current.right = newElement;
			
			current = newElement;
		}

		@Override
		public void setValue(T value) {
			current.value = value;
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
		flag = new Element();
		flag.left = flag;
		flag.right = flag;
	}

	/**
	 * @return un itÃ©rateur sur la liste, lâ€™Ã©lÃ©ment courant de lâ€™itÃ©rateur est
	 *         positionnÃ© sur lâ€™Ã©lÃ©ment de tÃªte.
	 */
	public ListIterator iterator() {
		return new ListIterator();
	}

	/**
	 * @return true si la liste est vide, false sinon
	 */
	public boolean isEmpty() {
		return flag.right == flag;
	}

	/**
	 * Supprimer toutes les valeurs de la liste.
	 */
	public void clear() {
		flag.left = flag;
		flag.right = flag;
	}

	/**
	 * Affecter la valeur v au drapeau.
	 * 
	 * @param v valeur Ã  mettre dans le drapeau.
	 */
	public void setFlag(T v) {
		flag.value = v;
	}

	/**
	 * Ajouter v en tÃªte de la liste.
	 * 
	 * @param v valeur Ã  ajouter
	 */
	public void addHead(T v) {
		Element head = new Element();
		head.value = v;
		head.left = flag;
		head.right = flag.right.right;
		
		flag.right.left = head;
		flag.right = head;
		
	}

	/**
	 * Ajouter v en queue de la liste.
	 * 
	 * @param v valeur Ã  ajouter
	 */
	public void addTail(T v) {
		Element tail = new Element();
		tail.value = v;
		tail.right = flag;
		tail.left = flag.left;
		
		flag.left.right = tail;
		flag.left = tail;
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