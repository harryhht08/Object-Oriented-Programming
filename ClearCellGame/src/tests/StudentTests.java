package tests;

import org.junit.Test;

public class StudentTests {
	@Test
	public void test01() {
		int[][] arr = new int[][] { { 1, 2, 3 }, { 1, 2 } };
		for (int[] a : arr)
			for (int b : a)
				System.out.print(b);

	}
}
