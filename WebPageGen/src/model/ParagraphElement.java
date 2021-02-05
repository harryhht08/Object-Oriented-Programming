package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement {

	private ArrayList<Element> arr;
	private String attributes;

	public ParagraphElement(String attributes) {
		super("p", true, null, attributes);
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
		s += "<p";
		if (enableId)
			s += " id=\"p" + super.getId() + "\"";
		if (attributes != null)
			s += " " + attributes;
		s += ">\n";
		for (Element e : arr) {
			s += e.genHTML(indentation + 3) + "\n";
		}
		for (int i = 0; i < indentation; i++) {
			s += " ";
		}
		s += "</p>";
		return s;
	}

}
