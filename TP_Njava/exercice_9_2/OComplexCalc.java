package exercice_9_2;

import java.util.Scanner;

public class OComplexCalc {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OComplex un = new OComplex(1, 0);
		OComplex z;
		OComplex resul;

		System.out.println(
				"Soit un nombre complexe x + y.i avec x et y des entiers.\nEntrez x et y séparer par un espace");

		z = new OComplex(sc.nextInt(), sc.nextInt());

		System.out.println("z = " + z.toString());

		resul = (((z.add(un)).mul(z)).add(un)).mul(z);

		System.out.println("z * (z * (z + 1) + 1) = " + resul.toString());

		sc.close();

	}

}
