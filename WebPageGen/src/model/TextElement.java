package model;

public class TextElement extends Object implements Element {

	private String text;

	@Override
	public String genHTML(int indentation) {
		String s = "";
		while (indentation > 0) {
			s += " ";
			indentation--;
		}
		s += text;
		return s;
	}

	public TextElement(String text) {
		this.text = text;
		
	}

}
