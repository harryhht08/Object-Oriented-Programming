package nestedStatic;

public class Driver {

	public static void main(String[] args) {
		System.out.println(OuterClass.staticVarOuter);
		System.out.println(OuterClass.nestedExample.staticVarNested);
		//System.out.println(OuterClass.nestedExample.nonStaticVarNested);  //will not compile
		
		OuterClass.nestedExample nE = new OuterClass.nestedExample();  //Notice not tied to an outer instance
		
		System.out.println(nE.nonStaticVarNested); 
		nE.someMethod();
		System.out.println(OuterClass.staticVarOuter);
	}
}
