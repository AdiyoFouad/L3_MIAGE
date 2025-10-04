package exercice_8_1;

public class NombreOccurences {

	static int nb0cc(String s, char c) {
		int nbr = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				nbr = nbr + 1;
			}
		}

		return nbr;
	}

	public static void main(String[] args) {
		System.out.print(nb0cc("abraacadabra", 'a'));
	}

}
