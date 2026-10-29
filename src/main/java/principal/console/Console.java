package principal.console;
import java.util.Scanner;
import principal.model.enums.Color;
import principal.controller.Controlador;

public class Console {
	private static Controlador controlador;
	private static Scanner sc = new Scanner(System.in);
	
	public Console() {
		controlador = new Controlador();
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
		while(turn < 10) {
			showGame(turn);
		}
	}
	
	public void showWinner(Color cor) {
		
	}
}
