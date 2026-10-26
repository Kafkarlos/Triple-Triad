package principal.model;

import java.util.HashMap;
import java.util.Map;
import org.jline.utils.AttributedStyle;

import principal.model.enums.Color;

public class Card {

	private int id;
	private String name;
	private Map<String, Integer> ranks;
	private String element;
	private boolean visible;
	private Color owner;
	
	public Card(int id, String name, String element, boolean visible, Color owner) {
		this.id = id;
		this.name = name;
		this.element = element;
		this.visible = false;
		this.owner = owner;
		this.ranks = new HashMap<String, Integer>();
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

	@Override
	public String toString() {
		return "Nome:" + name + ", ranks=" + ranks + "]";
	}
	
	
}
