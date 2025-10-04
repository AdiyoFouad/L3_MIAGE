package exercice_5_3;

public class Fibonacci {
	
	static int fiboIterative(int n) {
		int resul = 1;
		int fiboNMoins2 = 1;
		int fiboNMoins1 = 1;
		while(n > 1) {
			int temp = fiboNMoins1;
			
			resul = fiboNMoins1  + fiboNMoins2;
			
			fiboNMoins1 = resul;
			fiboNMoins2 =temp;
			n = n - 1;
		}
		return resul;
	}
	
	static int fiboRecursive(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return fiboRecursive(n - 1) + fiboRecursive( n - 2);
		}
	}

	public static void main(String[] args) {
		System.out.println(fiboIterative(50));
		System.out.println(fiboRecursive(50));

	}

}
