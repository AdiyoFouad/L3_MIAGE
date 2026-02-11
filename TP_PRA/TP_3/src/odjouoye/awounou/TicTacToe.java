package odjouoye.awounou;

import fr.istic.l3miage.pra.morpion.*;

public class TicTacToe implements AbstractTicTacToe {
	private Owner[][] partie = new Owner[3][3];
	private int nbreCoups;
	private Owner currentPlayer;

	public TicTacToe() {
		init();
	}
	
	private void init() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				partie[i][j] = Owner.NONE;
			}
		}
		nbreCoups = 0;
		currentPlayer = Owner.FIRST;
	}

	@Override
	public boolean gameOver() {
		return nbreCoups == 9 || (getWinner() != Owner.NONE);
	}

	@Override
	public Owner getSquare(int row, int col) {
		return partie[row][col];
	}

	@Override
	public Owner getTurn() {
		return currentPlayer;
	}

	@Override
	public Owner getWinner() {

		for (int i = 0; i < 3; i++) {
			if (partie[i][0] != Owner.NONE && partie[i][0] == partie[i][1] && partie[i][0] == partie[i][2]) {
				return partie[i][0];
			}

			if (partie[0][i] != Owner.NONE && partie[0][i] == partie[1][i] && partie[0][i] == partie[2][i]) {
				return partie[0][i];
			}
		}

		Owner o = partie[0][0];
		
		boolean diagonal1 = true;
		
		if (!o.equals(Owner.NONE)) {
			for (int i = 1; i < 3 && diagonal1; i++) {
				if (!o.equals(partie[i][i])) {
					diagonal1 = false;
				}
			}
			if(diagonal1) {
				return o;
			}
		}
		
		o = partie[0][2];
		
		boolean diagonal2 = true;
		
		if (!o.equals(Owner.NONE)) {
			for (int i = 0; i < 3 && diagonal2; i++) {
				if (!o.equals(partie[i][2 - i])) {
					diagonal2 = false;
				}
			}
			if(diagonal2) {
				return o;
			}
		}

		return Owner.NONE;
	}

	@Override
	public boolean legalMove(int row, int col) {
		if (!validSquare(row, col)) {
			throw new IllegalArgumentException();
		}
		return getSquare(row, col) == Owner.NONE && !gameOver();
	}

	@Override
	public void nextPlayer() {
		currentPlayer = currentPlayer.opposite();
	}

	@Override
	public int numberOfRounds() {
		return nbreCoups;
	}

	@Override
	public void play(int row, int col) {
		if (legalMove(row, col)) {
			nbreCoups++;
			partie[row][col] = currentPlayer;
			nextPlayer();
		}

	}

	@Override
	public void restart() {
		init();
	}

	@Override
	public boolean validSquare(int row, int col) {
		return 0 <= row && row < 3 && 0 <= col && col < 3;
	}
}
