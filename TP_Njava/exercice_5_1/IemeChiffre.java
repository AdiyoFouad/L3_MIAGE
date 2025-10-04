package exercice_5_1;

public class IemeChiffre {

	static int fncItterativeIemeChiffre(int n, int i) {
		int resul = 0;
		int nn = n;
		int k = 0;

		while (k <= i) {
			resul = nn % 10;
			nn = nn / 10;
			k++;
		}

		return resul;
	}

	static int fncRecursiveIemeChiffre(int n, int i) {
		if (i == 0) {
			return n % 10;
		} else {
			return fncRecursiveIemeChiffre(n / 10, i - 1);
		}
	}

	public static void main(String[] args) {

		System.out.println(fncItterativeIemeChiffre(123456789, 0));
		System.out.println(fncItterativeIemeChiffre(123456789, 1));
		System.out.println(fncItterativeIemeChiffre(123456789, 2));
		System.out.println(fncItterativeIemeChiffre(123456789, 3));
		System.out.println(fncItterativeIemeChiffre(123456789, 4));

		System.out.println(fncRecursiveIemeChiffre(123456789, 0));
		System.out.println(fncRecursiveIemeChiffre(123456789, 1));
		System.out.println(fncRecursiveIemeChiffre(123456789, 2));
		System.out.println(fncRecursiveIemeChiffre(123456789, 3));
		System.out.println(fncRecursiveIemeChiffre(123456789, 4));

	}

}
