package model;

public class HeadingElement extends TagElement {

	private Element content;
	private int level;
	private String attributes;

	public HeadingElement(Element content, int level, String attributes) {
		super("h" + level, true, content, attributes);
	}

}
