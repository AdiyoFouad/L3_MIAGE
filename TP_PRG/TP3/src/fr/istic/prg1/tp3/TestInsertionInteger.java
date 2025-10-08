package fr.istic.prg1.tp3;

import java.util.Scanner;

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
