package exercice_7_4;

public class SousSuite {

	static int longueurPlusLongueSousSuite(int[] t) {
		int maxLongueur = 0;
		int longueurActuelSousSuite = 1;
		
		for (int i = 0; i < t.length - 1; i++) {
			if (t[i] == t[i+1]) {
				longueurActuelSousSuite++;
			} else if (longueurActuelSousSuite > maxLongueur) {
				maxLongueur = longueurActuelSousSuite;
				longueurActuelSousSuite = 1;
			} else {
				longueurActuelSousSuite = 1;
			}
		}
		
		return maxLongueur;
	}
	
	public static void main(String[] args) {
		
		int[] suite = {7, 5, 5, 4, 1, 1, 1, 8, 8, 8, 4, 1};
		System.out.println(longueurPlusLongueSousSuite(suite));
		
	}

}
