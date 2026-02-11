package odjouoye.awounou.test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.l3miage.pra.morpion.*;
import odjouoye.awounou.TicTacToe;

public class TicTacToeTest {
	AbstractTicTacToe morpions;
	public static final int TAILLE = AbstractTicTacToe.BOARD_WIDTH;
	public static final int NB_CASES = AbstractTicTacToe.BOARD_WIDTH * AbstractTicTacToe.BOARD_HEIGHT;

	@BeforeEach
	public void setUp() {
		morpions = new TicTacToe(); //x
//		morpions = new TicTacToeV1(); //x
//		morpions = new TicTacToeV2(); //x
//		morpions = new TicTacToeV3(); //x
//		morpions = new TicTacToeV4(); //x
//		morpions = new TicTacToeV5(); //x
//		morpions = new TicTacToeV6(); //x
//		morpions = new TicTacToeV7(); //x
//		morpions = new TicTacToeV8(); //x
//		morpions = new TicTacToeV9(); 
//		morpions = new TicTacToeV10(); //x
//		morpions = new TicTacToeV11(); //x
//		morpions = new TicTacToeV12(); //x

	}

	/**
	 * fonction à appeler après chaque opération
	 */
	private void testInvariant() {
		// Le nombre de coups est positif ou nul, et inférieur ou égal au nombre de
		// cases
		assertTrue(morpions.numberOfRounds() >= 0, "Nombre de coups >= 0");
		assertTrue(morpions.numberOfRounds() <= NB_CASES, "Nombre de coups <= " + NB_CASES);

		// ----------------------
		// séquence à compléter
		// ----------------------
	}

	@Test
	public void testDouble() {
		morpions.play(0, 0);
		testInvariant();
		assertTrue(!morpions.legalMove(0, 0), "la case (0,0) est déjà remplie");
	}

	//@Test
	public void testNumberOfRounds() {
		morpions.play(0, 0);
		System.out.println(morpions.numberOfRounds());
		morpions.play(0, 1);
		System.out.println(morpions.numberOfRounds());
		morpions.play(0, 2);
		System.out.println(morpions.numberOfRounds());
		morpions.play(1, 0);
		System.out.println(morpions.numberOfRounds());
		morpions.play(1, 1);
		System.out.println(morpions.numberOfRounds());
		morpions.play(1, 2);
		System.out.println(morpions.numberOfRounds());
	}
	
	@Test
	public void testGetJoueur1() {
		Owner owner = morpions.getTurn();

		assertTrue(owner.equals(Owner.FIRST), "La méthode getTurn() doit retourner un Owner");
	
		morpions.play(1, 1);
		owner = morpions.getTurn();
		assertTrue(owner.equals(Owner.SECOND), "Le joueur courant après le premier coup ne peut être autre que le joueur SECOND");
		
		morpions.nextPlayer();
		owner = morpions.getTurn();
		assertTrue(owner.equals(Owner.FIRST), "");
	}
	
	@Test
	public void testGetJoueur2() {
	
		morpions.play(0, 0);
		morpions.play(0, 0);
		
		assertTrue(morpions.getTurn().equals(Owner.SECOND), "Après un play illégal on ne doit pas changer le joueur courant");		
		
		
	}
	
	@Test
	public void testInit() {
		// Scénario(s) vérifiant l’état du jeu avant le premier coup :
		// * non-fin de partie
		// * cases accessibles
		
		assertTrue(morpions.getTurn().equals(Owner.FIRST), "Le joueur courant au début de la partie ne peut être autre que le joueur FIRST");
		assertEquals(morpions.numberOfRounds(), 0, "Le nombre de coup doit être 0");

		assertTrue(morpions.getWinner().equals(Owner.NONE), "Au début de la partie on ne peut pas avoir de vainqueur");
		
		boolean caseVide = true;
		
		assertTrue(!morpions.gameOver(), "La partie ne peut être terminée avant le premier coup");
		for (int i = 0; i < TAILLE && caseVide; i++ ) {
			for (int j = 0; j < TAILLE && caseVide; j++ ) {
				if (!morpions.getSquare(i, j).equals(Owner.NONE) || !morpions.legalMove(i,j)) {
					caseVide = false;
				}
			}
		}
		assertTrue(caseVide, "Aucun joueur ne devrait encore jouer au début de la partie");
		
	}
	
	@Test
	public void testPremierCoup() {
		// Scénario vérifiant le premier coup joué, notamment :
		// * position correcte ou non
		// * non-fin de partie
		// * identité du premier joueur
		morpions.play(2, 1);
		assertTrue(morpions.getSquare(2, 1).equals(Owner.FIRST));
		assertTrue(!morpions.gameOver(), "La partie ne peut être terminée après le premier coup");
	}
	
	@Test
	public void testGetVainqueur0() {
		Owner winner = morpions.getWinner();
		assertTrue(winner.equals(Owner.NONE), "Au début de la partie on ne peut pas avoir de vainqueur");

		morpions.play(0, 0);
		winner = morpions.getWinner();
		assertTrue(winner.equals(Owner.NONE), "Après un coup on ne peut pas avoir de vainqueur");	
		
		morpions.play(1, 1);
		winner = morpions.getWinner();
		assertTrue(winner.equals(Owner.NONE), "Après 2 coups on ne peut pas avoir de vainqueur");	
		
		morpions.play(2, 1);
		winner = morpions.getWinner();
		assertTrue(winner.equals(Owner.NONE), "Après 3 coups on ne peut pas avoir de vainqueur");	
		
		morpions.play(0, 1);
		winner = morpions.getWinner();
		assertTrue(winner.equals(Owner.NONE), "Après 4 coups on ne peut pas avoir de vainqueur");	
		
	}
	
	@Test
	public void testGetVainqueur1() {
		//Victoire sur l'horizontal
		morpions.play(0, 0);
		morpions.play(1, 1);
		morpions.play(0, 1);
		morpions.play(2, 1);
		morpions.play(0, 2);
		assertTrue(morpions.getWinner().equals(Owner.FIRST) && morpions.numberOfRounds() == 5 && morpions.gameOver(), "TestGetVainqueur2 : Victoire horizontal (a)");
		
		morpions.restart();
		morpions.play(0, 0);
		morpions.play(1, 2);
		morpions.play(2, 1);
		morpions.play(1, 1);
		morpions.play(0, 2);
		morpions.play(2, 2);
		morpions.play(2, 0);
		morpions.play(1, 0);
		assertTrue(morpions.getWinner().equals(Owner.SECOND)&& morpions.numberOfRounds() == 8 && morpions.gameOver(), "TestGetVainqueur2 : Victoire horizontal (b)");
		
		morpions.restart();
		morpions.play(2, 2);
		morpions.play(0, 2);
		morpions.play(2, 0);
		morpions.play(1, 2);
		morpions.play(2, 1);
		assertTrue(morpions.getWinner().equals(Owner.FIRST)&& morpions.numberOfRounds() == 5 && morpions.gameOver(), "TestGetVainqueur2 : Victoire horizontal (c)");	
	}
	
	@Test
	public void testGetVainqueur2() {
		// Victoire sur la verticale
		morpions.play(2, 1);
		morpions.play(0, 0); 
		morpions.play(1, 1);
		morpions.play(1, 0);
		morpions.play(2, 2);
		morpions.play(2, 0);
		assertTrue(morpions.getWinner().equals(Owner.SECOND) && morpions.numberOfRounds() == 6 && morpions.gameOver(), "TestGetVainqueur3 : Victoire verticale (a)");

		morpions.restart();
		morpions.play(0, 1);
		morpions.play(0, 0);
		morpions.play(1, 1);
		morpions.play(2, 2);
		morpions.play(2, 1);
		assertTrue(morpions.getWinner().equals(Owner.FIRST) && morpions.numberOfRounds() == 5 && morpions.gameOver(), "TestGetVainqueur3 : Victoire verticale (b)");
		
		morpions.restart();
		morpions.play(0, 0);
		morpions.play(0, 2);
		morpions.play(1, 1);
		morpions.play(1, 2);
		morpions.play(2, 1);
		morpions.play(2, 2);
		assertTrue(morpions.getWinner().equals(Owner.SECOND) && morpions.numberOfRounds() == 6 && morpions.gameOver(), "TestGetVainqueur3 : Victoire verticale (c)");
	}
	
	@Test
	public void testGetVainqueur3() {
		//Victoire sur Diagonal

		morpions.play(0, 0);
		morpions.play(0, 1);
		morpions.play(1, 1);
		morpions.play(0, 2);
		morpions.play(2, 2);
		assertTrue(morpions.getWinner().equals(Owner.FIRST) && morpions.numberOfRounds() == 5 && morpions.gameOver(), "TestGetVainqueur3 : Victoire diagonale (a)");
		
		morpions.restart();
		morpions.play(1, 2);
		morpions.play(0, 2);
		morpions.play(1, 0);
		morpions.play(1, 1);
		morpions.play(0, 0);
		morpions.play(2, 0);
		assertTrue(morpions.getWinner().equals(Owner.SECOND) && morpions.numberOfRounds() == 6 && morpions.gameOver(), "TestGetVainqueur3 : Victoire diagonale (b)");
	}
	
	@Test
	public void testGetVainqueur4() {
		// Partie nul
		morpions.play(0, 2);
		morpions.play(0, 1);
		morpions.play(1, 0);
		morpions.play(0, 0);
		morpions.play(1, 1);
		morpions.play(2, 0);
		morpions.play(2, 1);
		morpions.play(1, 2);
		morpions.play(2, 2);
		assertTrue(morpions.getWinner().equals(Owner.NONE) && morpions.numberOfRounds() == 9 && morpions.gameOver(), "TestGetVainqueur4 : Match nul (b)");
		
	}
	
	@Test
	public void testPartiePasFinie() {
		// Scénario explorant les situations de non-fin de partie,
		// avec vérification systématique de gameOver() == false
		
		morpions.play(0, 2);
		assertTrue(!morpions.gameOver());
		
		morpions.play(0, 1);
		assertTrue(!morpions.gameOver());
		
		morpions.play(1, 0);
		assertTrue(!morpions.gameOver());
		
		morpions.play(0, 0);
		assertTrue(!morpions.gameOver());
		
		morpions.play(1, 1);
		assertTrue(!morpions.gameOver());
		
		morpions.play(2, 0);
		assertTrue(!morpions.gameOver());
	}
	
	@Test
	public void testControl0() {
		try {
			morpions.play(3, 3);
		} catch(Exception e) {
			return;
		}
		fail();
	}
	
	@Test
	public void testControle1() {
		// Scénario tentant divers coups non autorisés,
		// avec vérification systématique de legalMove()
		
		morpions.play(0, 0);
		assertTrue(!morpions.legalMove(0, 0), "la case (0,0) est déjà remplie");
		
		morpions.play(1, 1);
		assertTrue(!morpions.legalMove(1, 1), "la case (1,1) est déjà remplie");
		
		morpions.play(0, 1);
		assertTrue(!morpions.legalMove(0, 1), "la case (0,1) est déjà remplie");
		
		morpions.play(2, 1);
		assertTrue(!morpions.legalMove(2, 1), "la case (2,1) est déjà remplie");
		

		assertTrue(morpions.legalMove(2, 2), "la case (2,2) n'est pas encore remplie");
		
		morpions.play(0, 2);
		assertTrue(!morpions.legalMove(0, 2), "la case (0,2) est déjà remplie");
		
		assertTrue(!morpions.legalMove(2, 2), "Fin de partie ! On ne peut plus remplir de case");

		
	}
	
	@Test
	public void testControle2() {
		// Scénario tentant divers coups non autorisés,
		// avec vérification systématique de legalMove()
		

		morpions.play(0, 0);
		assertTrue(!morpions.legalMove(0, 0), "la case (0,0) est déjà remplie");
		
		morpions.play(1, 2);
		assertTrue(!morpions.legalMove(1, 2), "la case (1,2) est déjà remplie");
		
		morpions.play(2, 1);
		assertTrue(!morpions.legalMove(2, 1), "la case (2,1) est déjà remplie");
		
		morpions.play(1, 1);
		assertTrue(!morpions.legalMove(1, 1), "la case (1,1) est déjà remplie");
		
		morpions.play(0, 2);
		assertTrue(!morpions.legalMove(0, 2), "la case (0,2) est déjà remplie");
		
		morpions.play(2, 2);
		assertTrue(!morpions.legalMove(2, 2), "la case (2,2) est déjà remplie");
		
		morpions.play(2, 0);
		assertTrue(!morpions.legalMove(2, 0), "la case (2,0) est déjà remplie");
		

		assertTrue(morpions.legalMove(0, 1), "la case (0,1) n'est pas encore remplie");
		
		morpions.play(1, 0);
		assertTrue(!morpions.legalMove(1, 0), "la case (1,0) est déjà remplie");

		assertTrue(!morpions.legalMove(0, 1), "Fin de partie ! On ne peut plus remplir de case");
		
		
		
	}
	
	@Test
	public void testFinPartie() {
		// Scénarios explorant les situations de fin de partie
		morpions.play(0, 0);
		morpions.play(0, 1);
		morpions.play(1, 1);
		morpions.play(0, 2);
		morpions.play(2, 2);
		
		assertTrue(morpions.getWinner().equals(Owner.FIRST));
		
		morpions.play(2, 0);

		assertTrue(morpions.getSquare(2, 0).equals(Owner.NONE), "Après une fin de partie, aucune case ne doit être remplie");
	}
	
	@Test
	public void testFinPartie2() {
		// Scénarios explorant les situations de fin de partie

		morpions.play(0, 0);
		morpions.play(1, 1);	
		morpions.play(0, 1);
		morpions.play(2, 1);
		morpions.play(0, 2);
		
		assertTrue(morpions.getWinner().equals(Owner.FIRST));
//		System.out.println(morpions.getTurn());
		morpions.nextPlayer();
//		System.out.println(morpions.getTurn());
		
		
		morpions.play(2, 2);

		System.out.println(morpions.numberOfRounds());

		assertTrue(morpions.getSquare(2, 2).equals(Owner.NONE), "Après une fin de partie, aucune case ne doit être remplie");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
