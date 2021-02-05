package innerClass;

public class OuterClass {

	private int x; // instance field of outer

	public class MyInnerClass { // Considered a member of the outer class, just like the member variable, x

		private int y; // instance of inner
		// public static int staticVar = 0; //Not allowed

		public MyInnerClass(int y) {
			this.y = y;
		}

		public void innerMethod() {
			x = 1; // accessing private field of outer

			examplePrint();
			OuterClass.this.examplePrint(); // calling method of outer
		}

		public void examplePrint() {
			System.out.println("examplePrint of inner class: value of y: " + y);
		}

	}

	public void example() {
		MyInnerClass ic = new MyInnerClass(5);
		
		ic.y = 2; // accessing private field of inner
		// innerMethod(); //Not allowed to call like this, need a reference
		ic.innerMethod();
	}

	public void examplePrint() {
		System.out.println("examplePrint of Outer class");
	}

}
