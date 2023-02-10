/**
 * Class with methods to implement the basic arithmetic operations by operating
 * on bit fields.
 *
 * This is the skeleton code form COMP2691 Assignment #2.
 */
public class BinaryCalculator {
	 //**helper function to find twos complement
	public static BitField twosComplement(BitField b) {
		for (int i = 0; i < b.size(); i++) {
			if (b.get(i) == true) {
				b.set(i, false);
			} else {
				b.set(i, true);
			}
		}
		BitField temp = new BitField(b.size(), "1");
		BitField twosComplementB = add(temp, b);
		
		return twosComplementB;
	}
	//**helper function to left shift a bitfield
	public static BitField leftShift(BitField a, int shiftAmount) {
		//** take in the bitfield and make a copy to manipulate
		BitField value = new BitField(a.size());
		value = a.copy();
		
		//** shift the bits
		for(int i =shiftAmount; i < value.size() ; i++ ) {
			value.set(i, a.get(i - shiftAmount));
		}
		
		
		//**make the first bit equal to zero
				for(int i =0; i < shiftAmount ; i++) {
					value.set(i, false);
				}
				
		return value;
	}
	
	public static BitField add(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		// **create a bit field for the carry(all false)
		BitField c = new BitField(a.size());
		// **create a bit field for the result
		BitField result = new BitField(a.size());
		
		// **iterate through the bitfield
		for (int i = 0; i < a.size(); i++) {

			if (i != 0) {
				// find the result for carry
				if ((!a.get(i - 1) && b.get(i - 1) && c.get(i - 1)) 
						|| (a.get(i - 1) && !b.get(i - 1) && c.get(i - 1))
						|| (a.get(i - 1) && b.get(i - 1) && !c.get(i - 1))
						|| (a.get(i - 1) && b.get(i - 1) && c.get(i - 1))) {
					c.set(i, true);
				}
			}
			// find the value of sum
			if ((!a.get(i) && !b.get(i) && c.get(i))
					|| (!a.get(i) && b.get(i) && !c.get(i))
					|| (a.get(i) && !b.get(i) && !c.get(i))
					|| (a.get(i) && b.get(i) && c.get(i))) {
				result.set(i, true);
			}
		}
		return result;
	}

	public static BitField subtract(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		
		// ** find two complement of the subtracting number
		for (int i = 0; i < b.size(); i++) {
			if (b.get(i) == true) {
				b.set(i, false);
			} else {
				b.set(i, true);
			}
		}
		BitField temp = new BitField(b.size(), "1");
		BitField twosComplementB = add(temp, b);

		// ** add the 2's complement of B to A to subtract the two numbers
		return add(twosComplementB, a);
	}

	public static BitField multiply(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		
		//**BitFields that are double the size of the input fields
		BitField DoubleA = new BitField(a.size()*2, a.toString());
		BitField DoubleB = new BitField(b.size()*2, b.toString());

		//**used to manipulate bit string b
		BitField newB = new BitField(a.size()*2);
		BitField result = new BitField(a.size()*2);

	
		for(int i =0; i < a.size(); i++) {
			
			if(a.get(i) == true) {
				//**create a copy of the bitField and and left shift it 1
				newB = DoubleB.copy();
				newB = leftShift(newB, i);
			} else {
				//**create a BitField of all zeros
				newB = new BitField(DoubleB.size());
			}
			result = add(newB,result);
			
			
		}
		//** make the bitfield the appropriate size
		BitField value = new BitField(a.size());
		for(int i =0; i < value.size(); i++) {
			//System.out.println(result.get(i));
			value.set(i, result.get(i));
		}
		
		return value;
	}

	public static BitField[] divide(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}

		// Return both the quotient and the remainder
		BitField[] out = new BitField[2];
		out[0] = new BitField(a.size()); // quotient
		out[1] = new BitField(a.size()); // remainder
		return out;
	}
}
