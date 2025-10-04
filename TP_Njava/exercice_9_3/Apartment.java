package exercice_9_3;

public class Apartment {
	private String adresse;
	private int numEtage, surface, nbreActuelLocataire;
	
	public Apartment() {}
	
	public Apartment(String adresse, int numEtage, int surface, int nbreActuelLocataire) {
		this.adresse = adresse;
		this.numEtage = numEtage;
		this.surface = surface;
		this.nbreActuelLocataire = nbreActuelLocataire;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public int getNumEtage() {
		return numEtage;
	}
	
	public int getSurface() {
		return surface;
	}
	
	public int getNbreActuelLocataire() {
		return nbreActuelLocataire;
	}
	
	public String toString() {
		String numEtageStr;
		switch(numEtage) {
		case 0 : 
			numEtageStr = "rez-de-chaussée";
			break;
		case 1 : 
			numEtageStr = "1er étage";
			break;
		default : 
			numEtageStr = numEtage + "ème étage";
		}
		return "Cet appartement de superficie " + surface + "m² et situé au " + numEtageStr + " de l'immeuble à " + adresse + ", contient " + nbreActuelLocataire + " locataire(s) actuellement.";
	}

}
