package fr.istic.prg1.tp3;

import java.util.Scanner;

/**
 * @author	Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr>
 * 			Fouad ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr> 
 * @version 1.0
 * @since 2025-10-01
 * 
 *        Partie I
 * 
 *        Lecture, au clavier, d'une suite d'entiers terminée par -1
 * 
 *        Cette suite peut comporter des valeurs doubles.
 * 
 *        Construction et affichage d'un tableau contenant les entiers distincts
 *        triés par valeur croissante.
 * 
 *        Exemple. En entrée : 3 8 1 4 3 2 1 3 -1 ==> En sortie : 1 2 3 4 8
 */

public class InsertionInteger {

	private static final int SIZE_MAX = 10;
	private static final int END_MARKER = -1;
	/**
	 * nombre d’entiers présents dans t, 0 <= size <= SIZE_MAX
	 */
	private int size;
	private int[] array;

	/**
	 * @return copie de la partie remplie du tableau
	 */
	public int[] toArray() {
		return size == 0 ? new int[0] : array;
	}

	/**
	 * array est rempli, par ordre croissant, en utilisant la fonction insert, avec
	 * les valeurs lues par scanner.
	 * 
	 * @param scanner
	 */
	public void createArray(Scanner scanner) {
		int c = scanner.nextInt();
		if (c != END_MARKER) {
			insert(c);
		}
		while (c != END_MARKER) {
			insert(c);
			c = scanner.nextInt();
		}
	}

	/**
	 * Si value n'appartient pas à array[0..size-1] et size < SIZE_MAX, size est
	 * incrémenté de 1, value est inséré dans array et les entiers array[0..size]
	 * sont triés par ordre croissant. Sinon array est inchangé.
	 * 
	 * Exemple :
	 * 
	 * pour x = 5, size = 3, array[0] = 1, array[1] = 6, array[2] = 8
	 * 
	 * insertion délivre true, size = 4,
	 * 
	 * array[0] = 1, array[1] = 5, array[2] = 6, array[3] = 8
	 * 
	 * @param value valeur à insérer
	 * 
	 * @pre les valeurs de array[0..size-1] sont triées par ordre croissant
	 * 
	 * @return false si value appartient à array[0..size-1] ou si array est
	 *         complètement rempli; true si value n’appartient pas à
	 *         array[0..size-1]
	 */
	public boolean insert(int value) {

		int i = 0;
		int[] newArray = new int[size + 1];
		if (size == 0) {
			newArray[0] = value;
			array = newArray;
			size++;
			return true;
		}
		
		if (size == SIZE_MAX) {
			return false;
		}

		while (i < size - 1 && array[i] < value) {
			newArray[i] = array[i];
			i++;
		}
		// lorsqu'on sort de cette boucle le prochain élément du tableau est soit le 
		// dernier ou est celui qui doit suivre immédiatement value
		if (value == array[i]) {
			return false;
		} else if (value < array[i]) {
			// l'élément doit s'insérer entre deux éléments du tableau
			newArray[i] = value;
			for (int j = i; j < size; j++) {
				newArray[j + 1] = array[j];
			}
		} else {
			// value > array[i] : c'est que nous sommes à la fin du tableau
			newArray[i] = array[i];
			newArray[i + 1] = value;
		}

		array = newArray;
		size++;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < size; i++) {
			str.append(array[i]).append(" ");
		}
		return str.toString();
	}

	public static void main(String[] args) {


	}
}
