package innerClass;

public class Driver {

	public static void main(String[] args) {

		OuterClass myVar = new OuterClass();
		
		myVar.example();
		System.out.println("------");
		
		OuterClass.MyInnerClass ic = myVar.new MyInnerClass(8);
		
		
		ic.examplePrint();
	}

}
