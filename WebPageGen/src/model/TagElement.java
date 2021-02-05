package model;

public class TagElement extends Object implements Element {

	private String tagName;
	private boolean endTag;
	private Element content;
	private String attributes;
	protected static boolean enableId = false;
	private static int id = 0;
	private int id1;

	@Override
	public String genHTML(int indentation) {
		String s = new String();
		while (indentation > 0) {
			s += " ";
			indentation--;
		}
		s += "<" + tagName;
		if (enableId)
			s += " id=\"" + tagName + this.getId() + "\"";
		if (attributes != null)
			s += " " + attributes;
		s += ">";
		if (content != null)
			s += content.genHTML(indentation);
		if (endTag)
			s += "</" + tagName + ">";
		return s;
	}

	public TagElement(String tagName, boolean endTag, Element content, String attributes) {
		this.tagName = tagName;
		this.endTag = endTag;
		this.content = content;
		this.attributes = attributes;
		if (id == 0)
			id = 1;
		else
			id++;
		this.id1 = id;
	}

	public int getId() {
		return this.id1;
	}

	public String getStringId() {
		return tagName + id;
	}

	public java.lang.String getStartTag() {
		return null;

	}

	public java.lang.String getEndTag() {
		return null;

	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public static void resetIds() {
		id = 0;
	}

	public static void enableId(boolean choice) {
		enableId = choice;
	}

}
