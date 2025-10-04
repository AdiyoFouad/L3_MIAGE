 package exercice_7_5;

public class FusionTableaux {

	static int[] fusion(int[] t1, int[] t2) {
		int[] tab = new int[t1.length + t2.length];
		int i = 0;
		int j = 0;
		
		for (int k = 0; k < t1.length + t2.length; k++) {
			if (i >= t1.length) {
				tab[k] = t2[j];
				j++;
			} else if (j >= t2.length) {
				tab[k] = t1[i];
				i++;
			} else {
				if (t1[i] <= t2[j]) {
					tab[k] = t1[i];
					i++;
				} else {
					tab[k] = t2[j];
					j++;
				}
			}
		}
		
		
		return tab;
	}

	public static void main(String[] args) {
		int[] tab1 = { -5, 1, 5, 9, 10, 45, 78 };
		int[] tab2 = { -2, -1, 5, 10, 23 };
		int[] tab = fusion(tab1, tab2);
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
	}

}
