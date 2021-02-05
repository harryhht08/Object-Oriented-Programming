package model;

public class ImageElement extends TagElement {
	private String imageURL;
	private int width;
	private int height;
	private String alt;
	private String attributes;

	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", false, null, "src=\"" + imageURL + "\" width=\"" + width + "\" height=\"" + height + "\" alt=\""
				+ alt + "\"" + ((attributes == null || attributes.isBlank()) ? "" : " " + attributes));
		this.imageURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;
		this.attributes = attributes;
	}

	public String getImageURL() {
		return imageURL;
	}

}
