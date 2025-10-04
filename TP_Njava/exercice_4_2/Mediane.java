package exercice_4_2;

public class Mediane {
	
	static int mediane(int a, int b , int c) {
		if (b <= a && a <= c || c <= a && a <= b) {
			return a;
		}
		else if (a <= b && b <= c || c <= b && b <= a) {
			return b;
		}
		else {
			return c;
		}
	}

	public static void main(String[] args) {
		
		System.out.println("La médiane de 8, 1 et 4 est : " + mediane(8, 1, 4));
		System.out.println("La médiane de 4, 1 et 1 est : " + mediane(4, 1, 1));
		System.out.println("La médiane de 1, 4 et 4 est : " + mediane(1, 4, 4));
		
	}

}
