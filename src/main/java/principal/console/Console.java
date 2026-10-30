package principal.console;
import java.util.Scanner;
import principal.model.enums.Color;
import principal.model.Player;
import principal.controller.Controlador;


public class Console {
	private static Controlador controlador;
	private static Scanner sc = new Scanner(System.in);
	
	static {
		controlador = new Controlador(new Player(Color.RED),new Player(Color.BLUE));
	}	
	
	public static void iniciarConsole() {
		showRules();
		beginGame();
		
		boolean matchFinished = false;
		Color playerWinner = null;
		
		do {
			startBatlle();
			playerWinner = controlador.verifyMatchResult();
			matchFinished = playerWinner == Color.GRAY ? false : true;
			if(!matchFinished) {
				controlador.suddenDeath();
			}
			
		} while (!matchFinished);
		
		showWinner(playerWinner);
	}
	
	public static void showRules() {
		System.out.println(Rules.get());
		waitForEnter();
	}
	
	private static void beginGame() {
		controlador.startGame();
	}
	
	public static void showGame(int turn) {
		System.out.println(controlador.gameScenario(turn));
	}
	
	public static void startBatlle() {
		int turn = 0;
		
		while(turn < 9) {
			
	        String playerName = (turn % 2 == 0) ? "Jogador 1" : "Jogador 2";
	        String playerColor = (turn % 2 == 0) ? "RED" : "AZUL";
	        
	        waitForEnter(playerName,playerColor);
	        clearTerminal();
			showGame(turn);
			
			int row;
			int col;
			boolean validPosition = false;
			boolean validaPlayedCard = false;
 			
			do{
				row = lerInteiro("Informe a posição da linha onde deseja jogar a carta: ");
				col = lerInteiro("Informe a posição da coluna onde deseja jogar a carta: ");
				row--;
				col--;
				if(!controlador.checkPosition(row, col)) {
					System.out.println("Posição para posicionar carta inválida. Tente novamente");
				} else {
					validPosition = true;
				}
				if(!controlador.playCard(row,col,turn)) {
					System.out.println("Posição escolhida já possui carta ocupada. Tente novamente.");
				} else {
					validaPlayedCard = true;
				}
			}while(!(validPosition && validaPlayedCard));
				
			turn++;
			
		}
	}
	
	public static void waitForEnter(String playerName,String color) {
	    System.out.printf("\nVez de %s (%s) . Pressione ENTER para continuar...\n",playerName,color);
	    sc.nextLine();
	}
	
	public static void waitForEnter() {
	    System.out.printf("\nPressione ENTER para continuar...\n");
	    sc.nextLine();
	}
	
	public static void clearTerminal() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}
	
	private static void showWinner(Color color) {
		
			String playerName = (color == Color.RED) ? "Jogador 1" : "Jogador 2";
	        String playerColor = (color == Color.RED) ? "RED" : "AZUL";
	        
	        int[] scores = controlador.gameScore();

	        System.out.println();
	        System.out.println("==================================");
	        System.out.println("          TRIPLE TRIAD");
	        System.out.println("==================================");
	        System.out.println();
	        System.out.println("           FIM DE JOGO!");
	        System.out.println();
	        System.out.printf("       Vencedor: %s (%s)\n", playerName, playerColor);
	        System.out.println();
	        System.out.println("              PLACAR");
	        System.out.println("----------------------------------");
	        System.out.printf("       Jogador 1 (RED): %d pontos\n", scores[0]);
	        System.out.printf("       Jogador 2 (AZUL): %d pontos\n", scores[1]);
	        System.out.println("==================================");
	}
	
	public static int lerInteiro(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String entrada = sc.nextLine();

			try {
				return Integer.parseInt(entrada);

			} catch (NumberFormatException e) {
				System.out.println("Digite um numero inteiro valido.");
			}
		}
	}

}
