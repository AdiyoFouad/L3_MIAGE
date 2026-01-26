package odjouoye.awounou.test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.l3miage.pra.morpion.*;

public class TicTacToeTest {
	AbstractTicTacToe morpions;
	public static final int TAILLE = AbstractTicTacToe.BOARD_WIDTH;
	public static final int NB_CASES = AbstractTicTacToe.BOARD_WIDTH * AbstractTicTacToe.BOARD_HEIGHT;

	@BeforeEach
	public void setUp() {
//		morpions = new TicTacToeV1();
		morpions = new TicTacToeV2();
//		morpions = new TicTacToeV3();
//		morpions = new TicTacToeV4();
//		morpions = new TicTacToeV5();
//		morpions = new TicTacToeV6();
//		morpions = new TicTacToeV7();
//		morpions = new TicTacToeV8();
//		morpions = new TicTacToeV9();
//		morpions = new TicTacToeV10();
//		morpions = new TicTacToeV11();
//		morpions = new TicTacToeV12();

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

	@Test
	public void test1() {
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
	public void testGetJoueur() {
		Owner o = morpions.getTurn();
		assertTrue(o != null, "La méthode getTurn() doit retourner un Owner");
		//assertTrue(o = )
	}
	

}
