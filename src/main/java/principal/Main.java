package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import principal.model.Player;
import principal.model.TripleTriad;
import principal.model.enums.Color;

public class Main {

	public static void main(String[] args) {
		Player player1 = new Player(Color.RED);
		Player player2 = new Player(Color.BLUE);

		TripleTriad game = new TripleTriad(player1, player2);
		game.createRandomCards();
		game.dealTheCards();

		// as 9 posições do tabuleiro em ordem aleatória
		List<int[]> positions = new ArrayList<int[]>();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				positions.add(new int[] { row, col });
			}
		}
		Collections.shuffle(positions);

		Scanner scanner = new Scanner(System.in);
		
		int turn;
		for (turn = 0; turn < 9; turn++) {
			String vez = (turn % 2 == 0) ? "Jogador 1 (vermelho)" : "Jogador 2 (azul)";

			System.out.println("==================== Turno " + (turn + 1) + " - " + vez + " ====================");
			System.out.println(game.gameScenario(turn));

			int[] pos = positions.get(turn);
			System.out.println("Pressione ENTER para jogar em (" + pos[0] + "," + pos[1] + ")...");
			scanner.nextLine();

			String message = game.playCard(pos[0], pos[1], turn);
			System.out.println(message);
			System.out.println();
		}

		System.out.println("==================== Fim de jogo ====================");
		System.out.println(game.gameScenario(turn));
		System.out.println("Pontos -> Vermelho: " + player1.getPoints()
				+ " | Azul: " + player2.getPoints());
		System.out.println("Resultado: " + game.verifyMatchResult());

		scanner.close();
	}
}
