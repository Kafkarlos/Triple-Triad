package principal.model;

import java.util.HashMap;
import java.util.Map;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import principal.model.enums.Color;

public class Card {

	private int id;
	private String name;
	private Map<String, Integer> ranks;
	private String element;
	private boolean visible;
	private Player owner;
	
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
	
	public String getName() {
	    return name;
	}

	public int getRank(String direction) {
	    return ranks.getOrDefault(direction, 0);
	}

	@Override
    public String toString() {
		   String nomeColorido = new AttributedString(
			        name,
			        owner.getColor().getStyle()
			    ).toAnsi();

			    return String.format(
			        "%-10s%n  %d%n%d ★ %d%n  %d",
			        nomeColorido,
			        ranks.get("UP"),
			        ranks.get("LEFT"),
			        ranks.get("RIGHT"),
			        ranks.get("DOWN")
			    );
    }
	
	public String toStringOnBoard() {
	    AttributedStyle style = owner.getColor().getStyle();

	    String up = new AttributedString(
	        String.valueOf(ranks.get("UP")),
	        style
	    ).toAnsi();

	    String left = new AttributedString(
	        String.valueOf(ranks.get("LEFT")),
	        style
	    ).toAnsi();

	    String right = new AttributedString(
	        String.valueOf(ranks.get("RIGHT")),
	        style
	    ).toAnsi();

	    String down = new AttributedString(
	        String.valueOf(ranks.get("DOWN")),
	        style
	    ).toAnsi();

	    return String.format(
	        " %s %n%s %s%n %s ",
	        up, left, right, down
	    );
	}
}
