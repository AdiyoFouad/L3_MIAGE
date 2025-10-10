package fr.istic.prg1.tp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author 	Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr> 
 * 			Fouad ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr>
 * @version 1.0
 * @since 2025-10-01
 * 
 *        Classe de test qui permet de construire puis d'afficher, par ordre
 *        croissant, les doublets distincts positifs lus au clavier, ainsi que les 
 *        doublets distincts lus dans un fichier texte dont le nom est une chaîne 
 *        fournie au clavier
 */

public class TestInsertionPair {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		InsertionPair insertion = new InsertionPair();
		
        System.out.println("Entrez une suite d'entiers terminée par -1 :");

        insertion.createArray(scanner);

        System.out.println("Pair distincts triés par ordre croissant :");
        System.out.print(insertion);
        
        System.out.println("Veuillez entrez le nom du fichier contenant les doublets :");
        scanner = new Scanner(System.in);
        String fichier = scanner.nextLine();

		InsertionPair insertion2 = new InsertionPair();
		try {
			File file = new File(fichier);
			Scanner sc = new Scanner(file);
			insertion2.createArray(sc);
			System.out.print(insertion2);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas.");
		}
        
        scanner.close();

	}

}
