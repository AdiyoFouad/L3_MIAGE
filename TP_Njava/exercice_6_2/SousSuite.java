package exercice_6_2;

import java.util.Scanner;

public class SousSuite {

	static void longueurPlusLongueSousSuite() {

		Scanner sc = new Scanner(System.in);
		int maxLongueur = 1;
		int longueurActuelSousSuite = 1;
		int precedent;
		int n;

		System.out.println("Entrer une suite d'entiers positif terminée par -1 : ");

		precedent = sc.nextInt();
		n = sc.nextInt();

		while (n != -1) {

			if (precedent == n) {
				longueurActuelSousSuite = longueurActuelSousSuite + 1;
			} else {
				longueurActuelSousSuite = 1;
			}

			if (longueurActuelSousSuite > maxLongueur) {
				maxLongueur = longueurActuelSousSuite;
			}
			precedent = n;
			n = sc.nextInt();
		}

		System.out.println("La longueur de la plus grande sous-suite constante est : " + maxLongueur);
		sc.close();
	}

	public static void main(String[] args) {
		longueurPlusLongueSousSuite();

	}

}
