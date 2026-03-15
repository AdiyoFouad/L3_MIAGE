package odjouoye.awouno.morpion.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TicTacToeModel {

	/**
	 * Taille du plateau de jeu (pour être extensible).
	 */
	private final static int BOARD_WIDTH = 3;
	private final static int BOARD_HEIGHT = 3;

	/**
	 * Nombre de pièces alignés pour gagner (idem).
	 */

	private final static int WINNING_COUNT = 3;

	/**
	 * Joueur courant
	 */
	private final ObjectProperty<Owner> turn = new SimpleObjectProperty<>(Owner.FIRST);

	/**
	 * Vainqueur du jeu, NONE si pas de vainqueur.
	 */
	private final ObjectProperty<Owner> winner = new SimpleObjectProperty<>(Owner.NONE);

	private IntegerProperty nbreCoups = new SimpleIntegerProperty(0);

	/**
	 * Plateau de jeu.
	 */
	private final ObjectProperty<Owner>[][] board = new ObjectProperty[BOARD_WIDTH][BOARD_HEIGHT];

	/**
	 * Positions gagnantes.
	 */
	private final BooleanProperty[][] winningBoard = new BooleanProperty[BOARD_WIDTH][BOARD_HEIGHT];

	/**
	 * Constructeur privé
	 */
	private TicTacToeModel() {
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				board[i][j] = new SimpleObjectProperty<>(Owner.NONE);
				winningBoard[i][j] = new SimpleBooleanProperty(false);
			}
		}
		nbreCoups.set(0);
	}

	/**
	 * @return la seule instance possible du jeu
	 */
	public static TicTacToeModel getInstance() {
		return TicTacToeModelHolder.INSTANCE;
	}

	/**
	 * Classe interne selon le pattern singleton.
	 */
	private static class TicTacToeModelHolder {
		private static final TicTacToeModel INSTANCE = new TicTacToeModel();
	}

	public void restart() {
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				board[i][j].set(Owner.NONE);
				winningBoard[i][j].set(false);
			}
		}
		winner.set(Owner.NONE);
		turn.set(Owner.FIRST);
		nbreCoups.set(0);
	}

	/**
	 * @return joueur courant
	 */
	public final ObjectProperty<Owner> turnProperty() {
		return turn;
	}

	/**
	 * @return propriétaire de la case
	 */
	public final ObjectProperty<Owner> getSquare(int row, int column) {
		return board[row][column];
	}

	/**
	 * @return true si la case fait partie d’une combinaison gagnante
	 */
	public final BooleanProperty getWinningSquare(int row, int column) {
		return winningBoard[row][column];
	}

	/**
	 * Cette fonction ne doit donner le bon résultat que si le jeu est terminé.
	 * L’affichage peut être caché avant la fin du jeu.
	 *
	 * @return résultat du jeu sous forme de StringExpression
	 */
	public final StringExpression getEndOfGameMessage() {
		/**
		 * binding également sur nbreCoups car en cas de partie nul winner ne change pas
		 * nbreCoups seul suffira pas car dans le play j'incrémente nbreCoups avant de
		 * calculer le gagnant
		 */
		return Bindings.createStringBinding(() -> getMessage(), nbreCoups, winner);
	}

	private final String getMessage() {
		String msg;
		if (!gameOver().get()) {
			return "";
		}
		if (winner.get() == Owner.FIRST) {
			msg = "Game over! Le gagnant est le premier joueur";
		} else if (winner.get() == Owner.SECOND) {
			msg = "Game over! Le gagnant est le deuxième joueur";
		} else {
			msg = "Game over! Pas de vainqueur.";
		}
		return msg;
	}

	public void setWinner(Owner winner) {
		this.winner.set(winner);
	}

	public boolean validSquare(int row, int col) {
		return 0 <= row && row < BOARD_HEIGHT && 0 <= col && col < BOARD_WIDTH;
	}

	public void nextPlayer() {
		this.turn.set(this.turn.get().opposite());
	}

	/**
	 * Jouer dans la case (row, column) quand c’est possible.
	 */
	public void play(int row, int column) {
		if (!legalMove(row, column).get()) {
			return;
		}

		// nbreCoups.set(nbreCoups.get() + 1);
		(board[row][column]).set(turn.get());
		nextPlayer();

		if (nbreCoups.get() + 1 >= 5) { // Vérifie si au moins 5 coups ont été joués avant de checker
			checkWinner();
		}

		nbreCoups.set(nbreCoups.get() + 1); // Incrémente après le check

	}

	private void checkWinner() {

		// Vérification victoire horizontal
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			Owner o = board[i][0].get();
			if (o == Owner.NONE) {
				continue;
			}
			Boolean same = true;
			for (int j = 1; j < BOARD_WIDTH && same; j++) {
				if (board[i][j].get() != o) {
					same = false;
				}
			}
			if (same) {
				setWinner(o);
				for (int j = 0; j < BOARD_WIDTH; j++) {
					winningBoard[i][j].set(true);
				}
			}
		}

		// Vérification victoire vertical
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			Owner o = board[0][i].get();
			Boolean same = true;
			if (o == Owner.NONE) {
				continue;
			}
			for (int j = 1; j < BOARD_WIDTH && same; j++) {
				if (board[j][i].get() != o) {
					same = false;
				}
			}
			if (same) {
				setWinner(o);
				for (int j = 0; j < BOARD_HEIGHT; j++) {
					winningBoard[j][i].set(true);
				}
			}
		}

		Owner o = board[0][0].get();
		Boolean same = true;

		if (!o.equals(Owner.NONE)) {
			for (int i = 1; i < BOARD_HEIGHT && same; i++) {
				if (!o.equals(board[i][i].get())) {
					same = false;
				}
			}
			if (same) {
				setWinner(o);
				for (int i = 0; i < BOARD_HEIGHT; i++) {
					winningBoard[i][i].set(true);
				}
			}
		}

		o = board[0][BOARD_HEIGHT - 1].get();
		same = true;

		if (!o.equals(Owner.NONE)) {
			for (int i = 1; i < BOARD_HEIGHT && same; i++) {
				if (!o.equals(board[i][BOARD_HEIGHT - 1 - i].get())) {
					same = false;
				}
			}
			if (same) {
				setWinner(o);
				for (int i = 0; i < BOARD_HEIGHT; i++) {
					winningBoard[i][BOARD_HEIGHT - 1 - i].set(true);
				}
			}
		}

	}

	/**
	 * @return true s’il est possible de jouer dans la case c’est-à-dire la case est
	 *         libre et le jeu n’est pas terminé
	 */
	public BooleanBinding legalMove(int row, int column) {
		if (!validSquare(row, column)) {
			throw new IllegalArgumentException();
		}
		return Bindings
				.createBooleanBinding(() -> getSquare(row, column).get().equals(Owner.NONE) && !gameOver().get());
	}

	/**
	 * @return true si le jeu est terminé (soit un joueur a gagné, soit il n’y a
	 *         plus de cases à jouer)
	 */
	public BooleanBinding gameOver() {
		return Bindings.createBooleanBinding(() -> !winner.get().equals(Owner.NONE) || nbreCoups.get() == 9, nbreCoups,
				winner);
	}

	public IntegerProperty nbreCoupsProperty() {
		return nbreCoups;
	}

	/**
	 * private Owner[][] partie = new Owner[BOARD_WIDTH][BOARD_HEIGHT]; private int
	 * nbreCoups; private Owner currentPlayer;
	 * 
	 * public TicTacToeModel() { init(); }
	 * 
	 * private void init() { for (int i = 0; i < 3; i++) { for (int j = 0; j < 3;
	 * j++) { partie[i][j] = Owner.NONE; } } nbreCoups = 0; currentPlayer =
	 * Owner.FIRST; }
	 * 
	 * @Override public boolean gameOver() { return nbreCoups == 9 || (getWinner()
	 *           != Owner.NONE); }
	 * 
	 * @Override public Owner getSquare(int row, int col) { return partie[row][col];
	 *           }
	 * 
	 * @Override public Owner getTurn() { return currentPlayer; }
	 * 
	 * @Override public Owner getWinner() {
	 * 
	 *           for (int i = 0; i < 3; i++) { if (partie[i][0] != Owner.NONE &&
	 *           partie[i][0] == partie[i][1] && partie[i][0] == partie[i][2]) {
	 *           return partie[i][0]; }
	 * 
	 *           if (partie[0][i] != Owner.NONE && partie[0][i] == partie[1][i] &&
	 *           partie[0][i] == partie[2][i]) { return partie[0][i]; } }
	 * 
	 *           Owner o = partie[0][0];
	 * 
	 *           boolean diagonal1 = true;
	 * 
	 *           if (!o.equals(Owner.NONE)) { for (int i = 1; i < 3 && diagonal1;
	 *           i++) { if (!o.equals(partie[i][i])) { diagonal1 = false; } } if
	 *           (diagonal1) { return o; } }
	 * 
	 *           o = partie[0][2];
	 * 
	 *           boolean diagonal2 = true;
	 * 
	 *           if (!o.equals(Owner.NONE)) { for (int i = 0; i < 3 && diagonal2;
	 *           i++) { if (!o.equals(partie[i][2 - i])) { diagonal2 = false; } } if
	 *           (diagonal2) { return o; } }
	 * 
	 *           return Owner.NONE; }
	 * 
	 * @Override public boolean legalMove(int row, int col) { if (!validSquare(row,
	 *           col)) { throw new IllegalArgumentException(); } return
	 *           getSquare(row, col) == Owner.NONE && !gameOver(); }
	 * 
	 * @Override public void nextPlayer() { currentPlayer =
	 *           currentPlayer.opposite(); }
	 * 
	 * @Override public int numberOfRounds() { return nbreCoups; }
	 * 
	 * @Override public void play(int row, int col) { if (legalMove(row, col)) {
	 *           nbreCoups++; partie[row][col] = currentPlayer; nextPlayer(); }
	 * 
	 *           }
	 * 
	 * @Override public void restart() { init(); }
	 * 
	 * @Override public boolean validSquare(int row, int col) { return 0 <= row &&
	 *           row < 3 && 0 <= col && col < 3; }
	 **/
}
