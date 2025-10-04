package exercice_4_1;

public class Maximum {
	
	static int max(int a, int b) {
		if (a < b) {
			return b;
		} else {
			return a;
		}
	}
	
	static int max3v1(int a, int b, int c) {
		return max(max(a,b) , c);
	}
	
	static int max3v2(int a, int b, int c) {
		if (a >= b && a >= c) {
			return a;
		} else if (b >= a && b >= c) {
			return b;
		} else {
			return c;
		}
		
	}

	public static void main(String[] args) {
		System.out.println(max(5, 2));
		
		System.out.println(max3v1(5, 2, 15));
		System.out.println(max3v1(5, 15, 2));
		System.out.println(max3v1(15, 5, 2));
		
		System.out.println(max3v2(5, 2, 15));
		System.out.println(max3v2(5, 15, 2));
		System.out.println(max3v2(15, 5, 2));
	}

}
