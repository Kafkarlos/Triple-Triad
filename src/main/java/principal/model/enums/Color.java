package principal.model.enums;

import org.jline.utils.AttributedStyle;

public enum Color {
	RED(AttributedStyle.RED),
	BLUE(AttributedStyle.BLUE | AttributedStyle.BRIGHT);;
	
	private final int code;
	
	Color(int code){
		this.code = code;
	}
	
	public AttributedStyle getStyle() {
		return AttributedStyle.DEFAULT.foreground(code);
	}
}
