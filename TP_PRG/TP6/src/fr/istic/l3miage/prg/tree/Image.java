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
		
		itThis.addValue(itImage.getValue());
		
		if(itImage.getValue() == Node.TWO) {
			
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
		Iterator<Node> it = this.iterator();
		Iterator<Node> it1 = image1.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		auxUnion(it, it1, it2);
	}
	
	public Node auxUnion(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
		
		Node node1 = it1.getValue();
		Node node2 = it2.getValue();
		
		

		//System.out.println("Node 1 :" + node1);
		//System.out.println("Node 2 :" + node2);
		if (node1 == Node.ONE || node2 == Node.ONE) {
			it.addValue(Node.ONE);
			return  Node.ONE;
		} 
		
		if (node1 == Node.ZERO && node2 == Node.ZERO) {
			it.addValue(Node.ZERO);
			return Node.ZERO;
		}
		
		//L'un au moins des deux noeuds est TWO on doit descendre
		
		//it.setValue(Node.TWO);
		
		//it.goLeft();
		
		it.addValue(Node.TWO);
		
		it.goLeft();
		if (node1 == Node.TWO) it1.goLeft();
		if (node2 == Node.TWO) it2.goLeft();
		auxUnion( it, it1, it2);

		Node resultatG = it.getValue();
		it.goUp();
		if (node1 == Node.TWO) it1.goUp();
		if (node2 == Node.TWO) it2.goUp();
		
		it.goRight();
		if (node1 == Node.TWO) it1.goRight();
		if (node2 == Node.TWO) it2.goRight();
		auxUnion( it, it1, it2);
		Node w = it.getValue();
		it.goUp();
		if (node1 == Node.TWO) it1.goUp();
		if (node2 == Node.TWO) it2.goUp();
		
		//Gestion du cas où l'union donne une zone uniforme = pas besoin de diviser la zone
		if (resultatG == w && resultatG != Node.TWO) {
			it.goLeft();
			it.remove();
			it.goUp();
			it.remove();
			it.setValue(w);
			System.out.println("Un boss " + resultatG);
		}
		
		/*
		if (node1 == Node.TWO && node2 == Node.ZERO) {
			affectAux(it, it1);
		} else if (node2 == Node.TWO && node1 == Node.ZERO) {
			affectAux(it, it2);
		} else if (node1 == Node.TWO && node2 == Node.TWO) {
			it.addValue(Node.TWO);
			
			it.goLeft();
			it1.goLeft();
			it2.goLeft();
			Node n = auxUnion( it, it1, it2);
			it.goUp();
			it1.goUp();
			it2.goUp();
			
			it.goRight();
			it1.goRight();
			it2.goRight();
			Node w = auxUnion( it, it1, it2);
			it.goUp();
			it1.goUp();
			it2.goUp();
			
		}*/
		
		return Node.TWO;
		
		
		
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		Iterator<Node> it = this.iterator();
		
		if (it.getValue() == Node.TWO) {
			it.goLeft();
			boolean g = auxDiagonal(it, 0, 1);
			//System.out.println(g);
			if(!g) return false;
			it.goRoot();
			it.goRight();
			boolean d = auxDiagonal(it, 1, 1);
			//System.out.println(d);
			return d && g;
		} else {
			return it.getValue() == Node.ONE;
		}
	}
	

	public boolean auxDiagonal(Iterator<Node> it, int direction, int profondeur) {
		
		// direction vaut 0 on fait un parcours de l'arbre gauche et un parcours de l'arbre droit si direction vaut 1
		// profondeur nous permet de savoir si il faut parcour tous les deux sous-abres ou juste celui en fnc de la direction
		
		//System.out.println("Profondeur : " + (profondeur == 0 ? "pair" : "impaire") + " et " + (direction == 0 ? "gauche" : "droite"));
		if (it.getValue() == Node.TWO) {
			//System.out.println("noeud: " + it.getValue());
			if (profondeur == 1) {
				if (direction == 0) {
					it.goLeft();
					//System.out.println("left: " + it.getValue());
				} else {
					it.goRight();
					//System.out.println("rigth:" + it.getValue());
				}
				return auxDiagonal(it, direction, (profondeur + 1) % 2);	
			} else {
				it.goLeft();
				//System.out.println("left: " + it.getValue());
				boolean g = auxDiagonal(it, 0, (profondeur + 1) % 2);
				it.goUp();
				
				if(!g) return false; // plus besoin de faire la partie droite
				
				it.goRight();
				//System.out.println("rigth:" +it.getValue());
				boolean d = auxDiagonal(it, 1, (profondeur + 1) % 2);
				it.goUp();
				
				return g && d;
			}
			
		} else {
			return it.getValue() == Node.ONE;
		}
	}
	/*
	public boolean testDiagonalv0() {
		Iterator<Node> itG = this.iterator();
		Iterator<Node> itD = this.iterator();
		
		if (itG.getValue() == Node.TWO) {
			itG.goLeft();
			boolean g = auxDiagonal(itG, 0);
			System.out.println(g);
			
			itD.goRight();
			boolean d = auxDiagonal(itD, 1);
			System.out.println(d);
			return d && g;
		} else {
			return itD.getValue() == Node.ONE;
		}
		
	}
	
	public boolean auxDiagonalv0(Iterator<Node> it, int dir) {
		
		System.out.println(it.getValue());
		if (it.getValue() == Node.TWO) {
			if (dir == 0) {
				it.goLeft();
			} else {
				it.goRight();
			}
			return auxDiagonal(it, dir);
		} else {
			return it.getValue() == Node.ONE;
		}
	}
	*/

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