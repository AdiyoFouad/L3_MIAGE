package exercice_5_2;

public class Sum {

	static int sumOfDigitsItterative(int n) {
		int resul = 0;
		int nn = n;
		while (nn != 0) {
			resul += nn % 10;
			nn = nn / 10;
		}
		return resul;
	}

	static int sumOfDigitsRecursive(int n) {
		if (n == 0) {
			return 0;
		} else {
			return n % 10 + sumOfDigitsRecursive(n / 10);
		}
	}

	public static void main(String[] args) {

		System.out.println(sumOfDigitsItterative(103));
		System.out.println(sumOfDigitsRecursive(103));

	}

}
