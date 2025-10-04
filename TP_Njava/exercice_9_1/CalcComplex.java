package exercice_9_1;

import java.util.Scanner;

public class CalcComplex {
	
	static Complex add(Complex z1, Complex z2) {
		return new Complex(z1.x + z2.x, z1.y + z2.y);
	}
	
	static Complex mul(Complex z1, Complex z2) {
		// z1 * z2 = (ac - bd) + (ad + bc)i
		int x= z1.x * z2.x - z1.y * z2.y ;
		int y= z1.x * z2.y + z1.y * z2.x;
		return new Complex(x, y);
	}
	
	static String toString(Complex z) {
		String str;
		if (z.y == 0) {
			str ="" + z.x;
		} else if (z.x == 0 && z.y == 1) {
			str = "i";
		} else if (z.x == 0) {
			str = z.y + ".i";
		} else if (z.y == 1) {
			str = z.x + " + i" ;
		} else{
			str = z.x + " + " + z.y + ".i";
		}
		return str;
	}
	
	

	public static void main(String[] args) {
		
		System.out.println("Soit un nombre complexe x + y.i avec x et y des entiers.\nEntrez x et y séparer par un espace");
		Scanner sc = new Scanner(System.in);
		Complex z = new Complex(sc.nextInt(), sc.nextInt());
		System.out.println("z = " + toString(z));
		Complex un = new Complex(1,0);
		Complex resul = mul(z, add(mul(z, add(z, un)), un));
		System.out.println("z * (z * (z + 1) + 1) = " + toString(resul));
		sc.close();
		
	}

}
