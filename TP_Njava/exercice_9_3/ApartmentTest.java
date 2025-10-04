package exercice_9_3;

public class ApartmentTest {
	
	public static void afficheParc(Apartment[] parcImmobilier) {
		for(int i = 0; i < parcImmobilier.length; i++) {
			System.out.println(parcImmobilier[i]);
		}
	}
	
	public static int totalLocataires(Apartment[] parcImmobilier) {
		int sum = 0;
		for(int i = 0; i < parcImmobilier.length; i++) {
			sum += parcImmobilier[i].getNbreActuelLocataire();
		}
		return sum;
	}
	
	public static Apartment apptSurfaceMax(Apartment[] parcImmobilier) {
		Apartment max = parcImmobilier[0];
		for (int i = 1; i < parcImmobilier.length; i++) {
			if (parcImmobilier[i].getSurface() > max.getSurface()) {
				max = parcImmobilier[i];
			}
		}
		return max;
	}
	
	public static double surfaceMoyenne(Apartment[] parcImmobilier) {
		double somme = 0;
		for (int i = 0; i < parcImmobilier.length; i++) {
			somme += parcImmobilier[i].getSurface();
		}
		return somme / parcImmobilier.length;
	}

	public static void main(String[] args) {

		Apartment apart0 = new Apartment("1 Rue de Picardie", 0, 12, 3);
		Apartment apart1 = new Apartment("1 Rue de Picardie", 1, 11, 2);
		Apartment apart2 = new Apartment("1 Rue de Picardie", 2, 8, 1);
		Apartment apart3 = new Apartment("1 Rue de Picardie", 4, 16, 4);
		
		Apartment[] parcImmobilier = {apart0, apart1, apart2, apart3};
		
		afficheParc(parcImmobilier);

		System.out.println("Le nombre total de locataires dans ce parc immobilier est : " + totalLocataires(parcImmobilier));

		System.out.println("Appartement ayant la plus grande surface dans ce parc immobilier est : " + apptSurfaceMax(parcImmobilier));

		System.out.println("La superficie moyenne ce parc immobilier est : " + surfaceMoyenne(parcImmobilier) + "m²");

	}

}
