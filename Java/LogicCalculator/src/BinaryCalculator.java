/**
 * Class with methods to implement the basic arithmetic operations by
 * operating on bit fields.
 *
 * This is the skeleton code form COMP2691 Assignment #2.
 */
public class BinaryCalculator
{
    public static BitField add(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}
	//**create a bit field for the carry(all false)
	BitField c = new BitField(a.size());
	//**create a bit field for the result
	BitField result = new BitField(a.size());
	
	//**iterate through the bitfield
	for(int i =0; i < a.size(); i++ ) {
		if(i!=0) {
		//find the result for carry
		if((!a.get(i-1) && b.get(i-1) && c.get(i-1))
				||
			(a.get(i-1) && !b.get(i-1) && c.get(i-1))
				||
			(a.get(i-1) && b.get(i-1) && !c.get(i-1))
				||
			(a.get(i-1) && b.get(i-1) && c.get(i-1))
			) {
			c.set(i, true);
		}
		}
		//find the value of sum
		if((!a.get(i) && !b.get(i) && c.get(i))
				||
			(!a.get(i) && b.get(i) && !c.get(i))
				||
			(a.get(i) && !b.get(i) && !c.get(i))
				||
			(a.get(i) && b.get(i) && c.get(i))
			) {
			result.set(i, true);
		}
		
		
		
	}
	return result;
    }

    public static BitField subtract(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}
	return new BitField(a.size());
    }

    public static BitField multiply(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}
	return new BitField(a.size());
    }

    public static BitField[] divide(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}

	// Return both the quotient and the remainder
	BitField[] out = new BitField [ 2 ];
	out[0] = new BitField(a.size()); // quotient
	out[1] = new BitField(a.size()); // remainder
	return out;
    }
}
