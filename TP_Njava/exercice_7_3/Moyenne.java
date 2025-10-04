package exercice_7_3;

public class Moyenne {
	
	static double calculMoyenne(int[] t) {
		double somme = 0;
		for (int i = 0; i < t.length; i++) {
			somme += t[i];
		}
		return somme / t.length;
	}

	public static void main(String[] args) {
		int[] tab = {15, 25, 15};
		System.out.println(calculMoyenne(tab));
	}

}
