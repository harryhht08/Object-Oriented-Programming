package model;

import java.util.*;

public class WebPage extends Object implements Comparable<WebPage> {

	private ArrayList<Element> arr;
	private String title;
	private int count_l, count_p, count_t;
	private double tableUtil;

	@Override
	public int compareTo(WebPage o) {
		return this.title.compareTo(o.title);
	}

	public WebPage(String title) {
		arr = new ArrayList<Element>();
		this.title = title;
	}

	public int addElement(Element element) {
		if (element != null && element instanceof TagElement) {
			arr.add(element);
			if (element instanceof ListElement)
				count_l++;
			if (element instanceof ParagraphElement)
				count_p++;
			if (element instanceof TableElement) {
				count_t++;
				tableUtil = (tableUtil * (count_t - 1) + ((TableElement) element).getTableUtilization()) / count_t;
			}
			return ((TagElement) element).getId();
		}
		return -1;
	}

	public String getWebPageHTML(int indentation) {

		String s = "";
		s += "<!doctype html>\n" + "<html>\n";
		s += "   <head>\n" + "      <meta charset=\"utf-8\"/>\n" + "      <title>";
		s += this.title;
		s += "</title>\n" + "   </head>\n" + "   <body>\n";
		for (Element e : arr) {
			s += e.genHTML(indentation) + "\n";
		}
		s += "\n" + "   </body>\n" + "</html>";
		return s;
	}

	public void writeToFile(java.lang.String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}

	public Element findElem(int id) {
		for (Element e : arr)
			if (((TagElement) e).getId() == id)
				return e;
		return null;
	}

	public String stats() {
		String s = String.format(
				"List Count: %d\n" + "Paragraph Count: %d\n" + "Table Count: %d\n" + "TableElement Utilization: %.1f",
				count_l, count_p, count_t, tableUtil * 100);
		return s;
	}

	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}

}
