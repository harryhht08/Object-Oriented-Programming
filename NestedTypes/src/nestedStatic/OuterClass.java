package nestedStatic;

public class OuterClass {
	public int instanceVarOuter;
	static int staticVarOuter;

	public OuterClass() {
		instanceVarOuter = 1;
		staticVarOuter = 2;
		
	}

	public static class nestedExample {
		static int staticVarNested = 10;
		int nonStaticVarNested = 15;

		void someMethod() {
			//instanceVarOuter=5; //will not compile
			staticVarOuter = 20;
		}
	}
}
