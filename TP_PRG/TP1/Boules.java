public class Boules {

	final static int nombreBoules = 10;
	static char[] tableauBoules;
	static int r = 0;
	static int s = 0;
	static int t = nombreBoules - 1;


	public static char[] lireTableaux(){
		char[] tab = new char[Boules.nombreBoules];
		for (int i = 0; i < 10; i++){
			tab[i] = Lecture.lireChar();
		}
		return tab;
	}

	public static void ecrireTableauBoules(char[] tab){
		for (int i = 0; i < Boules.nombreBoules; i++){
			Ecriture.ecrireChar(tab[i]);
		}
	}
	public static void echange(int i, int j, char[] tab){
		char c = tab[i];
		tab[i] = tab[j];
		tab[j] = c;
	}

	public static void photo(int r, int s, int t, char[] tab){
		Ecriture.ecrireStringln("r = " + r + " s = " + s + " t = " + t);
		ecrireTableauBoules(tab);
		Ecriture.ecrireStringln("");
	}


	// vérifie les conditions 
	// 0 <= i < r => tab[i] == 'v'
	// r <= i < s => tab[i] == 'b'
	// s <= i <= t => tab[i] non trié (zone à parcourir)
	// t < i < n => tab[i] == 'r'
	public static boolean  verify(int r, int s, int t, char[] tab){
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

		Ecriture.ecrireString("Suite des " + Boules.nombreBoules + " boules : ");

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
				Ecriture.ecrireStringln("erreur : s = " + s + ", boule = " + Boules.tableauBoules[Boules.s] );
				++Boules.s;
			}
			photo(Boules.r, Boules.s, Boules.t, Boules.tableauBoules);
		}

		Ecriture.ecrireString("Résultat du tri : ");

		Boules.ecrireTableauBoules(Boules.tableauBoules);

		Ecriture.ecrireStringln();


	}

}