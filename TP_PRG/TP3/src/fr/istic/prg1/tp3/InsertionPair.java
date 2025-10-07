package fr.istic.prg1.tp3;

import java.util.Scanner;

/**
 * 
 * @author Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr> Fouad
 *         ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr>
 * @version 1.0
 * @since 2025-10-01
 * 
 *        Partie II
 *
 *        Lecture, au clavier, d'une suite de doublets d'entiers terminée par -1
 *
 *        Cette suite peut comporter des doublets identiques.
 * 
 *        Construction et affichage d'un tableau contenant les doublets
 *        distincts triés selon l'ordre croissant des doublets, à savoir : (x1,
 *        y1) < (x2, y2) <==> (x1<x2) ou (x1==x2 et y1<y2)
 * 
 *        En entrée : 3 8 1 4 3 8 1 3 -1 ==> En sortie : [1 3] [1 4] [3 8]
 */

public class InsertionPair {

	private static final int SIZE_MAX = 10;
	private static final int END_MARKER = -1;
	/**
	 * nombre de doublets présents dans t, 0 <= size <= SIZE_MAX
	 */
	private int size;
	private Pair[] array;

	/**
	 * @return copie de la partie remplie du tableau
	 */
	public Pair[] toArray() {
		Pair[] newArray = new Pair[size];
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i].copyOf();
		}
		return newArray;
	}

	/**
	 * array est rempli, par ordre croissant, en utilisant la fonction insert, avec
	 * les valeurs lues par scanner.
	 * 
	 * @param scanner
	 */
	public void createArray(Scanner scanner) {

		int x = scanner.nextInt();
		int y = scanner.nextInt();
		while (x != END_MARKER && y != END_MARKER) {
			insert(new Pair(x, y));
			x = scanner.nextInt();
			//y = scanner.nextInt();
			y = x != -1 ? scanner.nextInt() : END_MARKER;

		}
	}

	/**
	 * Si pair n'appartient pas à array[0..size-1] et size < SIZE_MAX, size est
	 * incrémenté de 1, pair est inséré dans array et les entiers array[0..size]
	 * sont triés par ordre croissant. Sinon array est inchangé.
	 * 
	 * @param pair doublet à insérer
	 * 
	 * @pre les doublets de array[0..size-1] sont triés par ordre croissant
	 * 
	 * @return false si pair appartient à array[0..size-1] ou si array est
	 *         complètement rempli; true si pair n’appartient pas à array[0..size-1]
	 */
	public boolean insert(Pair pair) {
		int i = 0;
		Pair[] newArray = new Pair[size + 1];
		if (size == SIZE_MAX) {
			return false;
		}

		if (size == 0) {
			newArray[0] = pair;
			array = newArray;
			size++;
			return true;
		}

		while (i < size - 1 && array[i].compareTo(pair) < 0) {
			newArray[i] = array[i];
			i++;
		}
		// lorsqu'on sort de cette boucle le prochain élément du tableau est soit le
		// dernier ou est celui qui doit suivre immédiatement value

		if (array[i].equals(pair)) {
			return false;
		} else if (0 < array[i].compareTo(pair)) {
			// l'élément doit s'insérer entre deux éléments du tableau
			newArray[i] = pair;
			for (int j = i; j < size; j++) {
				newArray[j + 1] = array[j];
			}
		} else {
			// value > array[i] : c'est que nous sommes à la fin du tableau
			newArray[i] = array[i];
			newArray[i + 1] = pair;
		}

		array = newArray;
		size++;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < size; i++) {
			str.append(array[i].toString()).append("\n");
		}
		System.out.println(str.toString());
		return str.toString();
	}

	public static void main(String[] args) {
		System.out.println("Entrer une suite d'entiers positif terminée par -1 : ");
		Scanner scanner = new Scanner(System.in);
		InsertionPair ourArray = new InsertionPair();
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		while (x != END_MARKER && y != END_MARKER) {
			ourArray.insert(new Pair(x, y));
			x = scanner.nextInt();
			//y = scanner.nextInt();
			y = x != -1 ? scanner.nextInt() : END_MARKER;

		}
		System.out.println(ourArray);
	}
}