package fr.istic.prg1.tp3;

/**
 * @author	Perrine BOISSY <perrine.boissy@etudiant.univ-rennes1.fr>
 * 			Fouad ODJOUOYE <fouad.odjouoye@etudiant.univ-rennes1.fr> 
 * @version 1.0
 * @since 2025-10-01
 * 
 */

public class Fourmis {

	/**
	 * @param s un terme de la suite des fourmis
	 * @pre s.length() > 0
	 * @return le terme suivant de la suite des fourmis
	 */
	public static String next(String s) {
		StringBuilder suite = new StringBuilder("");
		char curs, suiv;
		int compteur = 1;

		for (int i = 0; i < s.length(); i++) {
			curs = s.charAt(i);
			if (i == s.length() - 1) {
				// dernier élement et unique
				suite.append(compteur);
				suite.append(curs);
			} else if (i == s.length() - 2 && curs == s.charAt(i + 1)) {
				// avant dernier et dernier élement élément sont égaux
				suite.append(compteur + 1);
				suite.append(curs);
				i++; // je passe le cas du dernier élément puisque déjà traité
			} else if (i == s.length() - 2 && curs != s.charAt(i + 1)) {
				// avant dernier et dernier élement élément sont différents
				suite.append(compteur);
				suite.append(curs);
				suite.append(1);
				suite.append(s.charAt(i + 1));
				i++; // je passe le cas du dernier élément puisque déjà traité
			} else {
				// Cas où nous ne sommes pas sur les deux derniers numéros
				suiv = s.charAt(i + 1);
				if (curs == suiv) {
					compteur++;
				} else {
					suite.append(compteur);
					suite.append(curs);
					compteur = 1;
				}
			}
		}
		return suite.toString();
	}

	public static void main(String[] args) {
		System.out.println(next("13112221"));
	}
}