
public class testDriver {

	public static void main(String args[]) {
		//**test bitfields and BinaryCalculator
		BinaryCalculator bc = new BinaryCalculator();
		BitField a = new BitField("0111010000111100110101111110110000110111101101");
		BitField b = new BitField("0100110011101011010110101000100011111100011011");
		/*
		BitField DoubleA = new BitField(a.size()*2, a.toString());
		System.out.println(bc.leftShift(DoubleA, 3));
		*/
		
		/*
		System.out.println(a.toString());
		System.out.println(bc.leftShift(a, 2).toString());
		System.out.println(bc.add(a,b));
		*/
		System.out.println(bc.multiply(a, b).toString());
		String expected = "0101000001100001100111111000011100101011111111";
		String actual = "00100010111011001110110100001111111011010011100101000001100001100111111000011100101011111111";
		//System.out.println(expected.length() + "\n" + actual.length());
		//System.out.println("\n" + "0101000001100001100111111000011100101011111111");


		
		

		
	}
}
