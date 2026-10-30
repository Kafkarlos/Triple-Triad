package principal.console;
import java.io.IOException;
import java.util.Scanner;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;

import principal.controller.Controlador;
import principal.model.Player;
import principal.model.enums.Color;


public class Console {
	private static Controlador controlador;
	private static Scanner sc = new Scanner(System.in);
	private static Terminal terminal;

	
	static {
		controlador = new Controlador(new Player(Color.RED),new Player(Color.BLUE));
		
		try {
			terminal = TerminalBuilder.builder().system(true).build();
		} catch (IOException e) {
			terminal = null;  
		}
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
				 AttributedString text = new AttributedString(
						    "\n========= Morte Súbita =========", 
				            AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW)
				        );
				System.out.println("\n========= JOGO EMPATADO =========");
				System.out.println(text.toAnsi());
				controlador.suddenDeath();
			}
			
		} while (!matchFinished);
		
		showWinner(playerWinner);
	}
	
	public static void showRules() {
		System.out.println(Rules.get());
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
		
		clearTerminal();
		System.out.println(controlador.showBoard());
	}
	
	public static void waitForEnter(String playerName,String color) {
	    System.out.printf("\nVez de %s (%s) . Pressione ENTER para continuar...\n",playerName,color);
	    sc.nextLine();
	}
	
	public static void clearTerminal() {
		if (terminal != null && terminal.puts(InfoCmp.Capability.clear_screen)) {
			terminal.flush();
			return;
		}
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
