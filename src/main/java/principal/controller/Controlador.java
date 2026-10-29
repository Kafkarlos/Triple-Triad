package principal.controller;

import principal.model.Player;
import principal.model.TripleTriad;
import principal.model.enums.Color;

public class Controlador {

	private TripleTriad game;

	public Controlador(Player player1, Player player2) {
		this.game = new TripleTriad(player1, player2);
	}

	public void startGame() {
		game.createRandomCards();
		game.dealTheCards();
	}

	public String playCard(int row, int col, int turn) {
		return game.playCard(row, col, turn);
	}

	public String gameScenario(int turn) {
		return game.gameScenario(turn);
	}

	public boolean checkPosition(int row, int col) {
		return game.checkPosition(row, col);
	}

	public Color verifyMatchResult() {
		return game.verifyMatchResult();
	}

	public void suddenDeath() {
		game.suddenDeath();
	}
}