package exercice_8_3;

public class CompteMots {
	
	static int nbreDeMots(String phrase) {
		int nbre = 0;
		for(int i = 0; i < phrase.length()-1 ; i++) {
			if (phrase.charAt(i) == ' ' &&  phrase.charAt(i+1) != ' ') { 
				//Vérifie si on a un mot après l'espace
				nbre++;
			}
		}
		//La boucle for ne comptant que les mots après les espaces alors on ajoute le premier mot
		if (phrase.charAt(0) != ' ') {
			nbre++;
		}
		return nbre;
	}

	public static void main(String[] args) {
		String phrase =" Jean    dit  merci Alida. ";
		
		System.out.println(nbreDeMots(phrase));

	}

}
