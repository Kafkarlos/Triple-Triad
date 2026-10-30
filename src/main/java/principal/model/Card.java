package principal.model;

import java.util.HashMap;
import java.util.Map;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class Card {

	private int id;
	private String name;
	private Map<String, Integer> ranks;
	private String element;
	private boolean visible;
	private Player owner;
	
	public static final int INNER_WIDTH = 11;
	public static final int WIDTH = INNER_WIDTH + 2;  
	public static final int HEIGHT = 5;
	
	public Card(int id, String name, String element,HashMap<String,Integer> ranks) {
		this.id = id;
		this.name = name;
		this.element = element;
		this.visible = false;
		this.ranks = ranks;
	}
	
	public boolean compareValueOfLeft(Card adversaryCard) {
		if(ranks.get("LEFT") > adversaryCard.ranks.get("RIGHT")) {
			return true;
		}
		
		return false;
	}
	
	public boolean compareValueOfRight(Card adversaryCard) {
		if(ranks.get("RIGHT") > adversaryCard.ranks.get("LEFT")) {
			return true;
		}
		
		return false;
	}
	
	public boolean compareValueOfUp(Card adversaryCard) {
		if(ranks.get("UP") > adversaryCard.ranks.get("DOWN")) {
			return true;
		}
		
		return false;
	}
	
	public boolean compareValueOfDown(Card adversaryCard) {
		if(ranks.get("DOWN") > adversaryCard.ranks.get("UP")) {
			return true;
		}
		
		return false;
	}
	
	public boolean compareValueOf(String rank, Card adversaryCard) {
		switch (rank) {
			case "LEFT":
				return compareValueOfLeft(adversaryCard);
			case "UP":
				return compareValueOfUp(adversaryCard);
			case "RIGHT":
				return compareValueOfRight(adversaryCard);
			case "DOWN":
				return compareValueOfDown(adversaryCard);
			default:
				return false;
		}
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public void setOwner(Player player) {
		this.owner = player;
	}

	public String getName() { return name; }

	public int getRank(String direction) {
		Integer value = ranks.get(direction);
		return value == null ? 0 : value;
	}

	public boolean isVisible() { return visible; }

	public void setVisible(boolean visible) { this.visible = visible; }

	public String[] toLines() {
		String name = this.name == null ? "" : this.name;
		if (name.length() > INNER_WIDTH - 2) {
			name = name.substring(0, INNER_WIDTH - 2);
		}

		String[] plain = new String[HEIGHT];
		plain[0] = "┌" + repeat("─", INNER_WIDTH) + "┐";
		plain[1] = "│" + center(rankText("UP"), INNER_WIDTH) + "│";
		plain[2] = "│" + rankText("LEFT") + center(name, INNER_WIDTH - 2)
				+ rankText("RIGHT") + "│";
		plain[3] = "│" + center(rankText("DOWN"), INNER_WIDTH) + "│";
		plain[4] = "└" + repeat("─", INNER_WIDTH) + "┘";

		AttributedStyle style = AttributedStyle.DEFAULT;
		if (owner != null && owner.getColor() != null) {
			style = owner.getColor().getStyle().bold();
		}

		String[] out = new String[HEIGHT];
		for (int i = 0; i < HEIGHT; i++) {
			out[i] = new AttributedString(plain[i], style).toAnsi();
		}
		return out;
	}

	public static String[] emptyLines() {
		String[] plain = new String[HEIGHT];
		plain[0] = "┌" + repeat("─", INNER_WIDTH) + "┐";
		plain[1] = "│" + repeat(" ", INNER_WIDTH) + "│";
		plain[2] = plain[1];
		plain[3] = plain[1];
		plain[4] = "└" + repeat("─", INNER_WIDTH) + "┘";

		String[] out = new String[HEIGHT];
		for (int i = 0; i < HEIGHT; i++) {
			out[i] = new AttributedString(plain[i], AttributedStyle.DEFAULT.faint()).toAnsi();
		}
		return out;
	}

	@Override
	public String toString() {
		return String.join("\n", toLines());
	}

	private String rankText(String direction) {
		int value = getRank(direction);
		return value >= 10 ? "A" : String.valueOf(value); 
	}

	private static String center(String text, int width) {
		int total = Math.max(0, width - text.length());
		int left = total / 2;
		return repeat(" ", left) + text + repeat(" ", total - left);
	}

	private static String repeat(String s, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

}
