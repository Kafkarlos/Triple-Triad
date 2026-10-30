package principal.console;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class Rules {

	private static final int WIDTH = 78;  

	public static String get() {
		AttributedStringBuilder sb = new AttributedStringBuilder();

		heading(sb, "Regras do Jogo", '═');

		appendItem(sb, "• ", "",
			"O tabuleiro é uma grade de 3 espaços por 3 (total de 9 casas).");
		appendItem(sb, "• ", "",
			"Cada carta possui 4 números (um em cada lado: Cima, Baixo, Esquerda e Direita). "
			+ "Esses valores variam de 1 a 9, sendo que a letra “A” representa o maior valor "
			+ "possível (equivalente a 10).");
		appendItem(sb, "• ", "",
			"Um jogador coloca uma carta por turno em uma casa vazia do tabuleiro. Se o número "
			+ "da sua carta que estiver voltado para a carta do oponente for maior do que o "
			+ "número adjacente da carta dele, você captura essa carta, e ela muda para a sua cor.");
		appendItem(sb, "• ", "",
			"O jogo termina quando todas as 9 casas do tabuleiro forem preenchidas. Quem tiver "
			+ "o maior número de cartas sob seu controle no final da partida vence.");

		// ---
		sb.append(repeat("─", WIDTH), AttributedStyle.DEFAULT.faint());
		sb.append("\n\n");

		heading(sb, "Regras adicionais", '─');

		appendItem(sb, "1. ", "Three Open",
			": Três cartas de cada jogador são visíveis");
		appendItem(sb, "2. ", "Sudden Death",
			": O jogo termina quando um jogador vence; não são permitidos empates.");
		appendItem(sb, "3. ", "Order",
			": As cartas só podem ser jogadas na ordem em que aparecem na mão.");
		appendItem(sb, "4. ", "Combo",
			": Na regra Combo do Triple Triad (adaptada para disciplina), quando um jogador "
			+ "captura uma ou mais cartas no campo, essas cartas capturadas podem, por sua vez, "
			+ "capturar cartas adjacentes, gerando uma reação em cadeia.");

		return sb.toAnsi();
	}

	private static void heading(AttributedStringBuilder sb, String title, char underline) {
		sb.append(title, AttributedStyle.DEFAULT.bold());
		sb.append("\n");
		sb.append(repeat(String.valueOf(underline), title.length()), AttributedStyle.DEFAULT.faint());
		sb.append("\n\n");
	}

	private static void appendItem(AttributedStringBuilder sb, String prefix, String label, String body) {
		String text = label + body;
		int width = WIDTH - prefix.length();
		String indent = repeat(" ", prefix.length());
		int pos = 0;
		boolean first = true;

		while (pos < text.length()) {
			int end = Math.min(pos + width, text.length());
			if (end < text.length()) {
				int space = text.lastIndexOf(' ', end);
				if (space > pos) {
					end = space;
				}
			}

			sb.append(first ? prefix : indent);
			first = false;

			int boldEnd = Math.max(pos, Math.min(end, label.length()));
			if (boldEnd > pos) {
				sb.append(text.substring(pos, boldEnd), AttributedStyle.DEFAULT.bold());
			}
			if (end > boldEnd) {
				sb.append(text.substring(boldEnd, end));
			}
			sb.append("\n");

			pos = end;
			while (pos < text.length() && text.charAt(pos) == ' ') {
				pos++;
			}
		}
		sb.append("\n");
	}

	private static String repeat(String s, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(s);
		}
		return sb.toString();
	}
}