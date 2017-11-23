package io.t99.philesd.util;

public class NumberBaseConverter {

	public static Binary decToBin(int dec) {
		
		StringBuilder binaryString = new StringBuilder("1");
		int factor = 0;
		int i1 = 1;
		boolean found = false;
		
		if (dec == 0) {
			
			return new Binary("0");
			
		}
		
		while (!found) {
			
			if (i1 * 2 > dec) {
				
				factor = i1;
				found = !found;
				
			} else {
				
				binaryString.append("0");
				i1 *= 2;
				
			}
			
		}
		
		dec -= factor;
		
		for (int i2 = new Double(Math.log(factor)/Math.log(2)).intValue(); i2 >= 0; i2--) {
			
			if (factor <= dec) {
				
				dec -= factor;
				binaryString.replace(binaryString.length() - (i2 + 1), binaryString.length() - i2, "1");
				
			}
			
			factor /= 2;
			
		}
		
		while (binaryString.length() % 8 != 0) {
			
			binaryString.insert(0, "0");
			
		}
		
		return new Binary(binaryString.toString());

	}
	
	public static int binToDec(Binary bin) {
		
		int size = bin.getBinary().size() - 1;
		int dec = 0;
		
		for (int bit = 0; bit <= size; bit++) {
			
			if (bin.getBinary().get(size - bit)) {
				
				dec += Math.pow(2, bit);
				
			}
			
		}
		
		return dec;
		
	}

}
