package com.t99sdevelopment.philesd.util;

import java.util.ArrayList;

public class BitStream extends ArrayList<Boolean> {

	public BitStream() {}
	
	public BitStream(BitStream bitStream, int i) {
		
		if (i < 0 | !(i <= (bitStream.size() - 1))) {
			
			throw new IndexOutOfBoundsException();
			
		}
		
		for (int bit = i; bit <= (bitStream.size() - 1); bit++)  {
			
			this.add(bitStream.get(bit));
			
		}
		
	}
	
	public BitStream(BitStream bitStream, int i, int i2) {
		
		if (i > i2 | i < 0 | i2 > (bitStream.size() - 1)) {
			
			throw new IndexOutOfBoundsException();
			
		}
		
		for (int bit = i; bit < i2; bit++) {
			
			this.add(bitStream.get(bit));
			
		}
		
	}

}
