package principal;

import java.util.HashMap;
import java.util.Map;

import principal.model.Card;
import principal.model.Player;
import principal.model.enums.Color;

public class Main {
	public static void main(String[] args) {
		 Player dono = new Player(Color.RED);

	        HashMap<String, Integer> ranks = new HashMap<>();
	        ranks.put("UP", 7);
	        ranks.put("LEFT", 2);
	        ranks.put("RIGHT", 5);
	        ranks.put("DOWN", 4);

	        Card carta = new Card(1, "Dragão", "THUNDER", true, dono, ranks);

	        System.out.println(carta);
	        System.out.print(carta.toStringOnBoard());
	}
}
