package exercice_7_1;

public class IndiceMaximumTableau {
	
	static int indexOfMax(int[] t) {
		int indiceMax = 0;
		int maximum = t[0];
		
		for (int i = 1; i < t.length; i++) {
			if (t[i] > maximum) {
				maximum = t[i];
				indiceMax = i;
			}
		}
		
		return indiceMax;
	}

	public static void main(String[] args) {
		int[] tab = {3, 8, 5, 0, 2};
		System.out.println(indexOfMax(tab));

	}

}
