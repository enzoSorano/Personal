
public class testDriver {

	public static void main(String args[]) {
		BinaryCalculator bc = new BinaryCalculator();
		BitField a = new BitField("1011");
		BitField b = new BitField("1101");

		BitField result = bc.add(a, b);
		System.out.println(result.toString());
		
	}
}
