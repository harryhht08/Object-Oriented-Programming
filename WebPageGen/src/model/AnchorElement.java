package model;

public class AnchorElement extends TagElement {
	private String linkText;
	private String url;
	private String attributes;

	public AnchorElement(String url, String linkText, String attributes) {
		super("a", true, null, attributes);
		this.linkText = linkText;
		this.url = url;
	}

	public String getLinkText() {
		return this.linkText;
	}

	public java.lang.String getUrlText() {
		return this.url;
	}

	@Override
	public String genHTML(int indentation) {
		String s = new String();
		while (indentation > 0) {
			s += " ";
			indentation--;
		}
		s += "<a";
		if (enableId)
			s += " id=\"" + "a" + super.getId() + "\"";
		if (url != null)
			s += " href=\"" + url + "\"";
		if (attributes != null)
			s += " " + attributes;
		s += ">";
		if (linkText != null)
			s += linkText;
		s += "</a>";
		return s;

	}

}
