package fr.istic.prg1.tp3;

import java.util.Scanner;


/**
 * 
 * @author Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr> Fouad
 *         ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr>
 * @version 1.0
 * @since 2025-10-01
 * 
 *        Classe de test qui permet de construire puis d'afficher, par ordre croissant,
 *        les entiers distincts lus au clavier
 */

public class TestInsertionInteger {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        InsertionInteger insertion = new InsertionInteger();

        System.out.println("Entrez une suite d'entiers terminée par -1 :");

        insertion.createArray(scanner);

        System.out.println("Entiers distincts triés par ordre croissant :");
        System.out.println(insertion.toString());

        scanner.close();

	}

}
