package model;

public class TableElement extends TagElement {
	private Element[][] arr;
	private String attributes;
	private int count;
	private double size;
	private String tagName;
	private int row;
	private int col;

	public TableElement(int rows, int cols, String attributes) {
		super("table", true, null, attributes);
		arr = new Element[rows][cols];
		this.attributes = attributes;
		count = 0;
		size = rows * cols;
		tagName = "table";
		row = rows;
		col = cols;
	}

	public double getTableUtilization() {
		return ((double) count) / size;

	}

	public void addItem(int rowIndex, int colIndex, Element item) {
		if (item != null) {
			count++;
			arr[rowIndex][colIndex] = item;
		}
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
		for (int i = 0; i < row; i++) {
			s += indent_space + "   " + "<tr>";
			for (int j = 0; j < col; j++) {
				s += "<td>";
				if (arr[i][j] != null)
					s += arr[i][j].genHTML(0);
				s += "</td>";
			}
			s += "</tr>\n";
		}

		s += indent_space + "</" + tagName + ">";
		return s;
	}

}
