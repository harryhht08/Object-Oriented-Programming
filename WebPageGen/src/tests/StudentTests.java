package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AnchorElement;
import model.HeadingElement;
import model.ImageElement;
import model.ListElement;
import model.ParagraphElement;
import model.TableElement;
import model.TagElement;
import model.TextElement;
import model.WebPage;

public class StudentTests {
	public static final String TESTS_TAG = "\n\nEndTest";

	@Test
	public void pubTableTest1() {
		int indentation = 3;
		String attributes = "border=\"1\"", answer = "";

		TagElement.resetIds();
		TagElement.enableId(true);
		TableElement tableElement = new TableElement(2, 2, attributes);
		tableElement.addItem(0, 0, new TextElement("John"));
		tableElement.addItem(0, 1, new TextElement("Laura"));
		tableElement.addItem(1, 0, new TextElement("Rose"));

		answer += tableElement.genHTML(indentation);
		answer += TESTS_TAG;

		assertTrue(TestsSupport.isCorrect("pubTableTest1.txt", answer));
	}

	@Test
	public void pubWebPageTest1() {
		int indentation = 3;
		String answer = "";

		TagElement.resetIds();
		TagElement.enableId(false);
		WebPage webPage = new WebPage("Example1");
		answer = webPage.getWebPageHTML(indentation);
		answer += TESTS_TAG;

		assertTrue(TestsSupport.isCorrect("pubWebPageTest1.txt", answer));
	}

	@Test
	public void pubWebPageTest2() {
		WebPage webPage = new WebPage("Example1");
		int indentation = 3;
		String headingAttributes = null, paragraphAttributes = null, answer = "";

		TagElement.resetIds();
		TagElement.enableId(false);
		webPage.addElement(new HeadingElement(new TextElement("Introduction"), 1, headingAttributes));
		ParagraphElement paragraph = new ParagraphElement(paragraphAttributes);
		paragraph.addItem(new TextElement("Fear the turtle"));
		paragraph.addItem(new ImageElement("testudo.jpg", 200, 300, "Testudo Image", ""));
		webPage.addElement(paragraph);

		answer += webPage.getWebPageHTML(indentation);
		answer += TESTS_TAG;

		assertTrue(TestsSupport.isCorrect("pubWebPageTest2.txt", answer));
	}

	@Test
	public void pubWebPageTest3() {
		WebPage webPage = new WebPage("Example2");
		int indentation = 3;
		String answer = "";

		TagElement.resetIds();
		TagElement.enableId(false);
		TableElement tableElement = new TableElement(2, 2, null);
		tableElement.addItem(0, 0, new TextElement("Dog"));
		tableElement.addItem(1, 1, new TextElement("Cat"));
		webPage.addElement(tableElement);

		tableElement = new TableElement(2, 2, null);
		tableElement.addItem(0, 0, new TextElement("Red"));
		tableElement.addItem(0, 1, new TextElement("Blue"));
		tableElement.addItem(1, 0, new TextElement("Green"));
		tableElement.addItem(1, 1, new TextElement("Yellow"));
		webPage.addElement(tableElement);

		webPage.addElement(new ListElement(true, null));

		answer += webPage.getWebPageHTML(indentation);
		answer += "\n" + webPage.stats();
		answer += TESTS_TAG;
		System.out.print(answer);

		assertTrue(TestsSupport.isCorrect("pubWebPageTest3.txt", answer));
	}

//	@Test
//	public void tryout() {
//		String s1 = "ffff";
//		String s2 = s1;
//		s1 += "a";
//
//		System.out.print(s1);
//		System.out.println();
//		System.out.print(s2);
//
//		String s3 = new String("mmmmm");
//		String s4 = s3;
//		s4 += "ooo";
//		System.out.print(s3);
//		System.out.println();
//		System.out.print(s4);
//
//	}

//	@Test
//	public void doubleandint() {
//		double b = 2;
//		System.out.print(((double)2)/1);
//	}
}
