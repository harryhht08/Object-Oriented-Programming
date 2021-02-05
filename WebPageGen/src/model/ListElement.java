package model;

import java.util.ArrayList;

public class ListElement extends TagElement {
	private ArrayList<Element> arr;
	private String tagName;
	private String attributes;

	public ListElement(boolean ordered, String attributes) {
		super((ordered ? "ol" : "ul"), true, null, attributes);
		tagName = ordered ? "ol" : "ul";
		this.attributes = attributes;
		arr = new ArrayList<Element>();
	}

	public void addItem(Element item) {
		if (item != null)
			arr.add(item);
	}

	@Override
	public String genHTML(int indentation) {

		String s = "";
		for (int i = 0; i < indentation; i++) {
			s += " ";
		}
		String indent_space = s;
		s += "<" + tagName;
		if (enableId)
			s += " id=\"" + tagName + this.getId() + "\"";
		if (attributes != null)
			s += " " + attributes;
		s += ">\n";
		for (Element e : arr) {
			s += indent_space + "   " + "<li>\n";
			s += e.genHTML(indentation + 6) + "\n";
			s += indent_space + "   " + "</li>\n";
		}

		s += indent_space + "</" + tagName + ">";
		return s;

	}

}
