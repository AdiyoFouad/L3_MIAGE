package exercice_8_2;

public class Palindrome {

	static boolean estPalindrome(String s) {


		boolean resul = true;

		for (int i = 0; i < s.length() / 2 && resul; i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				resul = false;
			}
		}

		return resul;

	}

	public static void main(String[] args) {
		
		System.out.println(estPalindrome("kayak"));
		System.out.println(estPalindrome("ABBA"));
		System.out.println(estPalindrome("LOL"));
		System.out.println(estPalindrome("baba"));
	}

}
