package exercice_6_1;

import java.util.Scanner;

public class Moyenne {
	
	static void calculMoyenne() {
		System.out.println("Entrer une suite d'entiers positif terminée par -1 : ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nbre = 0;
		int somme = 0;
		
		while (n != -1) {
			somme = somme + n;
			nbre++;
			n = sc.nextInt();
		}
		
		System.out.println("La moyenne est : " + somme * 1.0 / nbre);
		sc.close();
		
	}
	
	public static void main(String[] args) {
		calculMoyenne();
	}

}
