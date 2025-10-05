import java.util.Scanner;

public class Boules {

	final static int NOMBRESBOULES = 10;
	static char[] tableauBoules;
	static int r = 0;
	static int s = 0;
	static int t = NOMBRESBOULES - 1;

	public static char[] lireTableaux(){
		char[] tab = new char[Boules.NOMBRESBOULES];
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();  // Lire la ligne complète d'un coup
    
    	// Vérifier que l'entrée contient bien 10 caractères (en ignorant les espaces)
    	input = input.replaceAll("\\s", "");  // Retirer les espaces
		// Remplir le tableau avec les caractères
		if (input.length() != Boules.NOMBRESBOULES) {
			System.out.println("Erreur : il faut entrer exactement " + Boules.NOMBRESBOULES + " boules.");
			return lireTableaux();  // Relancer la méthode si l'entrée est incorrecte
		}

		for (int i = 0; i < Boules.NOMBRESBOULES; i++) {
			tab[i] = input.charAt(i);  // Remplir le tableau avec les caractères saisis
		}
		return tab;
	}

	public static void ecrireTableauBoules(char[] tab){
		for (int i = 0; i < Boules.NOMBRESBOULES; i++){
			System.out.print(tab[i]);  // Utilisation de System.out.print pour afficher un char
		}
	}

	public static void echange(int i, int j, char[] tab){
		char c = tab[i];
		tab[i] = tab[j];
		tab[j] = c;
	}

	public static void photo(int r, int s, int t, char[] tab){
		System.out.println("r = " + r + " s = " + s + " t = " + t);  
		ecrireTableauBoules(tab);  // Appel de la méthode pour afficher le tableau
		System.out.println();  
	}


	// vérifie les conditions 
	// 0 <= i < r => tab[i] == 'v'
	// r <= i < s => tab[i] == 'b'
	// s <= i <= t => tab[i] non trié (zone à parcourir)
	// t < i < n => tab[i] == 'r'
	public static boolean verify(int r, int s, int t, char[] tab){
		int n = tab.length;
		for (int i = 0; i < n; i++){
			if (i < r ) {
				if (tab[i] != 'v') return false;
			} else if (i < s ) {
				if (tab[i] != 'b') return false;
			} else if (i <= t ) {
				// zone encore à trier : on n'impose rien
			} else { // i > t
				if (tab[i] != 'r') return false;
			}
		}
		return true;
	}


	public static void main(String[] args){
		System.out.print("Suite des " + Boules.NOMBRESBOULES + " boules : ");  

		Boules.tableauBoules = Boules.lireTableaux();

		while(Boules.s <= Boules.t){
			switch(Boules.tableauBoules[Boules.s]){
			case 'v':
				Boules.echange(Boules.r, Boules.s, Boules.tableauBoules);
				++Boules.r;
				++Boules.s;
				break;
			case 'b':
				++Boules.s;
				break;
			case 'r':
				Boules.echange(Boules.s, Boules.t, Boules.tableauBoules);
				--Boules.t;
				break;
			default:
				System.out.println("erreur : s = " + s + ", boule = " + Boules.tableauBoules[Boules.s] );  
				++Boules.s;
			}
			photo(Boules.r, Boules.s, Boules.t, Boules.tableauBoules);
		}

		System.out.print("Résultat du tri : ");  

		Boules.ecrireTableauBoules(Boules.tableauBoules);

		System.out.println();  
	}

}
