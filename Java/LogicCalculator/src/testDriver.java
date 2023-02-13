
public class testDriver {

	public static void main(String args[]) {
		//**test bitfields and BinaryCalculator
		BinaryCalculator bc = new BinaryCalculator();
		BitField a = new BitField("0101");
		BitField b = new BitField("1101");
		
		System.out.println(bc.divide(a,b));
		/*
		BitField DoubleA = new BitField(a.size()*2, a.toString());
		System.out.println(bc.leftShift(DoubleA, 3));
		*/
		
		/*
		System.out.println(a.toString());
		System.out.println(bc.leftShift(a, 2).toString());
		System.out.println(bc.add(a,b));
		*/


		
		

		
	}
}
