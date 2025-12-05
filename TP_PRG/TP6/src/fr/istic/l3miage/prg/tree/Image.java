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
		Iterator<Node> it = this.iterator();
		it.clear();
		auxRotate180(it, image2.iterator());
	}
	public void auxRotate180(Iterator<Node> itThis,Iterator<Node> itImage) {
		Node current = itImage.getValue();
		itThis.addValue(current);
		
		if (current == Node.TWO) {
			itThis.goRight();
			itImage.goLeft();
			auxRotate180(itThis, itImage);
			itThis.goUp();
			itImage.goUp();

			itThis.goLeft();
			itImage.goRight();
			auxRotate180(itThis, itImage);
			itThis.goUp();
			itImage.goUp();
		}
		
		
	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		auxVideoInverse(this.iterator()); 
	}
	
	public void auxVideoInverse(Iterator<Node> it) {
		Node current = it.getValue();
		if (current == Node.TWO) {
			
			it.goLeft();
			auxVideoInverse(it);
			it.goUp();
			
			it.goRight();
			auxVideoInverse(it);
			it.goUp();
			
		} else {
			it.setValue(current == Node.ONE ? Node.ZERO : Node.ONE);
		}
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		it.clear();
		auxMirrorV(it, image2.iterator(), 0);
	}
	
	public void auxMirrorV(Iterator<Node> itThis, Iterator<Node> itImage, int profondeur) {
		Node current = itImage.getValue();
		itThis.addValue(current);
		
		if (current == Node.TWO) {
			if ( profondeur % 2 == 1) {
				itThis.goLeft();
				itImage.goLeft();
				auxMirrorV(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
				

				itThis.goRight();
				itImage.goRight();
				auxMirrorV(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
			} else {

				itThis.goRight();
				itImage.goLeft();
				auxMirrorV(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
				

				itThis.goLeft();
				itImage.goRight();
				auxMirrorV(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
			}
		}
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		it.clear();
		auxMirrorH(it, image2.iterator(), 0);
	}
	
	public void auxMirrorH(Iterator<Node> itThis, Iterator<Node> itImage, int profondeur) {
		Node current = itImage.getValue();
		itThis.addValue(current);
		
		if (current == Node.TWO) {
			if ( profondeur % 2 == 0) {
				itThis.goLeft();
				itImage.goLeft();
				auxMirrorH(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
				

				itThis.goRight();
				itImage.goRight();
				auxMirrorH(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
			} else {

				itThis.goRight();
				itImage.goLeft();
				auxMirrorH(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
				

				itThis.goLeft();
				itImage.goRight();
				auxMirrorH(itThis, itImage, profondeur + 1);
				itThis.goUp();
				itImage.goUp();
			}
		}
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
		Iterator<Node> it = this.iterator();
		it.clear();
		Iterator<Node> itImage = image2.iterator();
		
		if (itImage.getValue() == Node.TWO) {
			itImage.goLeft();
		}
		if (itImage.getValue() == Node.TWO) {
			itImage.goLeft();
		}
		affectAux(it, itImage);
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
		Iterator<Node> it = this.iterator();
		Iterator<Node> itImage = image2.iterator();

		it.clear();

		it.addValue(Node.TWO);
		it.goRight();
		it.addValue(Node.ZERO);
		it.goUp();
		it.goLeft();
		it.addValue(Node.TWO);
		
		
		it.goRight();
		it.addValue(Node.ZERO);
		it.goUp();

		it.goLeft();
		
		auxZoomOut(it, itImage, 0);

		/**
		 * notre auxZoomOut notre it à Node.ZERO 
		 * c"est à dire la compression donne un noir
		 * dans ce cas toute l'image est noir alors
		 */
		if(it.getValue() == Node.ZERO) {
			it.goRoot();
			it.clear();
			it.addValue(Node.ZERO);
		}
		
	}
	
	public void auxZoomOut(Iterator<Node> itThis, Iterator<Node> itImage, int profondeur) {

		if (profondeur < 14) {
			
			itThis.addValue(itImage.getValue());
			if(itImage.getValue() == Node.TWO) {
				
				itThis.goLeft();
				itImage.goLeft();
				auxZoomOut(itThis, itImage, profondeur + 1);
				Node resultatG = itThis.getValue();
				itImage.goUp();
				itThis.goUp();
				
				itThis.goRight();
				itImage.goRight();
				auxZoomOut(itThis, itImage, profondeur + 1);
				Node resultatD = itThis.getValue();
				itImage.goUp();
				itThis.goUp();	
				
				if (resultatG == resultatD && resultatG != Node.TWO) {
					itThis.goLeft();
					itThis.remove();
					itThis.goUp();
					itThis.remove();
				}

			}
			
		} else {
			if(itImage.getValue() != Node.TWO) {
				itThis.addValue(itImage.getValue());
			} else {
				calcul(itThis, itImage);
			}		
			
		}
		
		
		
		
	}
	
	public void calcul(Iterator<Node> itThis, Iterator<Node> itImage) {

		Node current = itImage.getValue();

		//itThis.addValue(current);
		
		if (current == Node.TWO) {
			int nbreNoir = 0;
			int nbreBlanc = 0;
			
			itImage.goLeft();
			if (itImage.getValue() == Node.ZERO) {
				nbreNoir += 2;
			} else if (itImage.getValue() == Node.ONE) {
				nbreBlanc += 2;
			} else {
				nbreBlanc++;
				nbreNoir++;
			}
			itImage.goUp();
			
			itImage.goRight();
			if (itImage.getValue() == Node.ZERO) {
				nbreNoir += 2;
			} else if (itImage.getValue() == Node.ONE) {
				nbreBlanc += 2;
			} else {
				nbreBlanc++;
				nbreNoir++;
			}
			itImage.goUp();
			
			if( nbreNoir > nbreBlanc) {
				itThis.addValue(Node.ZERO);
			} else {
				itThis.addValue(Node.ONE);
			}
			
			
			//System.out.println(itThis.getValue());

			
		}
		
		
		
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
		Iterator<Node> it = this.iterator();
		Iterator<Node> it1 = image1.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		auxIntersection(it, it1, it2);
	}
	
	public void auxIntersection(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
		
		Node node1 = it1.getValue();
		Node node2 = it2.getValue();
		
		if (node1 == Node.ZERO || node2 == Node.ZERO) {
			it.addValue(Node.ZERO);
			return ;
		}
		
		if(node1 == Node.ONE && node2 == Node.ONE) {
			it.addValue(Node.ONE);
			return ;
		}
		
		it.addValue(Node.TWO);
		
		it.goLeft();
		if (node1 == Node.TWO) it1.goLeft();
		if (node2 == Node.TWO) it2.goLeft();
		
		auxIntersection(it, it1, it2);
		Node resultatG = it.getValue();
		
		it.goUp();
		if (node1 == Node.TWO) it1.goUp();
		if (node2 == Node.TWO) it2.goUp();
		
		it.goRight();
		if (node1 == Node.TWO) it1.goRight();
		if (node2 == Node.TWO) it2.goRight();
		
		auxIntersection(it, it1, it2);
		Node resultatD = it.getValue();
		
		it.goUp();
		if (node1 == Node.TWO) it1.goUp();
		if (node2 == Node.TWO) it2.goUp();
		
		if (resultatD == resultatG && resultatD != Node.TWO) {
			it.goLeft();
			it.remove();
			it.goUp();
			it.remove();
		}
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
	
	public void auxUnion(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
		
		Node node1 = it1.getValue();
		Node node2 = it2.getValue();
		
		

		//System.out.println("Node 1 :" + node1);
		//System.out.println("Node 2 :" + node2);
		if (node1 == Node.ONE || node2 == Node.ONE) {
			it.addValue(Node.ONE);
			return ;
		} 
		
		if (node1 == Node.ZERO && node2 == Node.ZERO) {
			it.addValue(Node.ZERO);
			return ;
		}
		
		//L'un au moins des deux noeuds est TWO on doit descendre
		
		
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
		Node resultatD = it.getValue();
		it.goUp();
		if (node1 == Node.TWO) it1.goUp();
		if (node2 == Node.TWO) it2.goUp();
		
		//Gestion du cas où l'union donne une zone uniforme = pas besoin de diviser la zone
		if (resultatG == resultatD && resultatG != Node.TWO) {
			it.goLeft();
			it.remove();
			it.goUp();
			it.remove();
			/*
			 * le it.remove() ici ne bouge pas l'itérateur
			 * il met juste le courant à null et si le courant à un fils
			 * le fils prend sa place
			 * 
			 */
		}
		
		
		
		
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
	            	minH = medianeH;
	                it.goRight();
	            }
	        } else { //profondeur paire on divise les x (découpage verticale)
	        	int medianeW = (minW + maxW) / 2;
	            if (x < medianeW) {
	            	maxW = medianeW;
	                it.goLeft();
	            } else {
	            	minW = medianeW;
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
		int profondeur = 0;
	    int minH = 0;
	    int maxH = 256;
	    int minW = 0;
	    int maxW = 256;


	    Iterator<Node> it = this.iterator();
	    
	    while (it.getValue() == Node.TWO) {
	    	if (profondeur % 2 == 0) {
	    		int midH = (minH + maxH) / 2;
	    		if (y1 < midH) {
	    			maxH = midH;
	    			it.goLeft();
	    		} else {
	    			minH = midH;
	    			it.goRight();
	    		}
	    	} else {
	    		int midW = (minW + maxW) / 2;
	    		if (x1 < midW) {
	    			maxW = midW;
	    			it.goLeft();
	    		} else {
	    			minW = midW;
	    			it.goRight();
	    		}
	    	}
	    	profondeur++;
	    }
		return minW <= x2 && x2 < maxW && minH <= y2 && y2 < maxH;
		/**
		 * J'ai pas fait strictement inférieur au niveau des max car j'ai choisi 
		 * 256 comme valeur max et il n'est pas inclu dans les valeurs possibles
		 * De plus si on fait les ( / 2) les parties inférieurs sont bornés par
		 * les valeurs impaire inclus (par exemple 0 à 127 et non 0 à 128)
		 */
	}

	/**
	 * @param image2 autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		return auxIsIncludedIn(this.iterator(), image2.iterator());
	}
	
	public boolean auxIsIncludedIn(Iterator<Node> itThis, Iterator<Node> itImage) {
		Node currentThis = itThis.getValue();
		Node currentImage = itImage.getValue();
		
		if (currentThis == Node.ZERO || currentImage == Node.ONE) {
			return true;
		} else if (currentThis != currentImage) {
			return false;
		} else { // Les deux sont égals à 2
			itThis.goLeft();
			itImage.goLeft();
			boolean resultatG = auxIsIncludedIn(itThis, itImage);
			itThis.goUp();
			itImage.goUp();
			
			if (!resultatG) return false;
			
			itThis.goRight();
			itImage.goRight();
			boolean resultatD = auxIsIncludedIn(itThis, itImage);
			itThis.goUp();
			itImage.goUp();
			
			return resultatD;
		}
		
	}
}