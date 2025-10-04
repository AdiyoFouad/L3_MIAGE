package exercice_7_2;

public class MaximumTableau {


	static int maxTab(int[] t) {
		int maximum = t[0];
		
		for (int i = 1; i < t.length; i++) {
			if (t[i] > maximum) {
				maximum = t[i];
			}
		}
		
		return maximum;
	}

	public static void main(String[] args) {
		int[] tab = {3, 8, 5, 0, 2};
		System.out.println(maxTab(tab));

	}
}
