package principal.console;
import java.util.Scanner;
import principal.model.enums.Color;
import principal.model.Player;
import principal.controller.Controlador;


public class Console {
	private static Controlador controlador;
	private static Scanner sc = new Scanner(System.in);
	
	public Console() {
		controlador = new Controlador(new Player(Color.RED),new Player(Color.BLUE));
	}	
	
	public void iniciarConsole() {
		
	}
	public void showRules() {
		
	}
	public void beginGame() {
		
	}
	public static void showGame(int turn) {
		
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
				
				if(!controlador.checkPosition(row, col)) {
					System.out.println("Posição para posicionar carta inválida. Tente novamente");
				}
				
				if(!controlador.playCard(row,col,turn)) {
					System.out.println("Posição escolhida já possui carta ocupada. Tente novamente.");
				}
			}while(!(validPosition && validaPlayedCard));
				
			turn++;
			
		}
	}
	
	public static void waitForEnter(String playerName,String color) {
	    System.out.printf("\nVez de %s (%s) . Pressione ENTER para continuar...\n",playerName,color);
	    sc.nextLine();
	}
	
	public static void clearTerminal() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}
	
	public void showWinner(Color cor) {
		
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
