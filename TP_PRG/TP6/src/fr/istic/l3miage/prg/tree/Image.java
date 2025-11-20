package fr.istic.l3miage.prg.tree;

import java.util.Scanner;

import fr.istic.l3miage.prg.tree_util.AbstractImage;
import fr.istic.l3miage.prg.tree_util.Iterator;
import fr.istic.l3miage.prg.tree_util.Node;


/**
 * @author ODJOUOYE Fouad <fouad.odjouoye@etudiant.univ-rennes1.fr>
 * @version 5.0
 * @since 2025-11-20
 * 
 *        Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * this devient identique à image2.
	 *
	 * @param image2 image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		if (this == image2) return;
		Iterator<Node> it = this.iterator();
		it.clear();
		affectAux(it, image2.iterator());
	}
	
	public void affectAux(Iterator<Node> itThis, Iterator<Node> itImage) {
		
		if(itImage.getValue() == Node.TWO) {
			itThis.addValue(itImage.getValue());
			
			itThis.goLeft();
			itImage.goLeft();
			affectAux(itThis, itImage);
			itImage.goUp();
			itThis.goUp();
			
			itThis.goRight();
			itImage.goRight();
			affectAux(itThis, itImage);
			itImage.goUp();
			itThis.goUp();	
		} else {
			itThis.addValue(itImage.getValue());
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2 image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2 image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this devient
	 * éteint.
	 * 
	 * @param image2 image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}

	/**
	 * @param x abscisse du point
	 * @param y ordonnée du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
	    int profondeur = 0;
	    int minH = 0;
	    int maxH = 256;
	    int minW = 0;
	    int maxW = 256;


	    Iterator<Node> it = this.iterator();

	    while (it.getValue() == Node.TWO) {
	        if (profondeur % 2 == 0) { //profondeur impaire on divise les y (découpage horizontale)
	            int medianeH = (minH + maxH) / 2;
	            if (y < medianeH) {
	            	maxH = medianeH;
	                it.goLeft();
	            } else {
	            	minH = medianeH + 1;
	                it.goRight();
	            }
	        } else { //profondeur paire on divise les x (découpage verticale)
	        	int medianeW = (minW + maxW) / 2;
	            if (x < medianeW) {
	            	maxW = medianeW;
	                it.goLeft();
	            } else {
	            	minW = medianeW + 1;
	                it.goRight();
	            }
	        }
	    	
	        /*
	        System.out.println("Ittération : " + profondeur);
	        System.out.println("NodeType: " + it.getValue());
	        System.out.println("Width : [ " +  + minW + " ; " + maxW + " ]");
	        System.out.println("Height : [ " + minH + " ; " + maxH + " ]");
	        System.out.println("X : " + x);
	        System.out.println("Y : " + y);
	        System.out.println("");
	        
	        */
	        profondeur++;
	    }

	    return it.getValue() == Node.ONE;
	}

	/**
	 * @param x1 abscisse du premier point
	 * @param y1 ordonnée du premier point
	 * @param x2 abscisse du deuxième point
	 * @param y2 ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par la
	 *         même feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}

	/**
	 * @param image2 autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}
}