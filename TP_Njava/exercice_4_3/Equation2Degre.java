package exercice_4_3;

public class Equation2Degre {
	
	static String resolution(double a, double b, double c) {
		double discriminant = b*b - 4 * a * c;
		if(a == 0 && b == 0 && c == 0) {
			return "Cette équation a une infinité de solution";
		} else if (a == 0 && b != 0) {
			double solution = -c/b;
			return "Cette équation a une solution unique : x = " + solution ;
		} else if (a != 0 && discriminant == 0) {
			double solution = -b/(2 * a);
			return "Cette équation a une solution unique : x = " + solution ;
		} else if(a != 0 && discriminant > 0) {
			double solution1 = (-b - Math.sqrt(discriminant)) / (2 * a);
			double solution2 = (-b + Math.sqrt(discriminant)) / (2 * a);
			return "Cette équation a deux solutions : x1 = " + solution1 + " et x2 = " + solution2; 
		} else {
			return "Cette équation n'a pas de solution";
		}
	}

	public static void main(String[] args) {
		System.out.println(resolution(0, 5, 1));

	}

}
