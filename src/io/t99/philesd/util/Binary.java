package io.t99.philesd.util;

import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Binary {
	
	public static final int FORMAT_NO_LEADING_ZEROS = 0;
	public static final int FORMAT_NO_LEADING_ZEROS_WITH_SPACES = 1;
	public static final int FORMAT_FULL_BYTES = 2;
	public static final int FORMAT_FULL_BYTES_WITH_SPACES = 3;
	
	private ArrayList<Boolean> bbyte = new ArrayList<>();
	
	public Binary() {}
	
	public Binary(String sbyte) throws MalformedParametersException {
	
		sbyte = sbyte.trim();
		sbyte = sbyte.replaceAll("\\s+","");
		
		for (int i = 0; i < sbyte.length(); i++) {
			
			if (!sbyte.substring(i, i + 1).equals("1") && !sbyte.substring(i, i + 1).equals("0")) {
				
				throw new MalformedParametersException();
				
			} else {
				
				if (sbyte.substring(i, i + 1).equals("1")) {
					
					bbyte.add(Boolean.TRUE);
					
				} else {
					
					bbyte.add(Boolean.FALSE);
					
				}
				
			}
			
		}
	
	}
	
	public Binary(Binary bin, int i, int i2) throws MalformedParametersException {
		
		if (!(i2 > i) | i < 0 | !(i2 <= bin.getBinary().size())) { // if i2 is not greater than i, or if
			
			throw new MalformedParametersException();
			
		}
		
		ArrayList<Boolean> oldset = bin.getBinary();
		
		if (oldset.size() >= i2) { // TODO - this if statement might be able to go, not going to devote the brain power rn
			
			for (int bit = i; bit < i2; bit++) {
				
				bbyte.add(oldset.get(bit));
				
			}
			
		}
		
	}
	
	public Binary(Binary bin, int i) {
		
		if (i < 0 | !(i <= bin.getBinary().size() - 1)) { // if i2 is not greater than i, or if
			
			// honestly I don't want to have to deal with the exception
			
		}
		
		ArrayList<Boolean> oldset = bin.getBinary();
		
		for (int bit = i; bit <= oldset.size() - 1; bit++) {
			
			bbyte.add(oldset.get(bit));
			
		}
		
	}
	
	public void append(Binary bin) {
		
		bbyte.addAll(bin.getBinary());
		
	}
	
	public void append(boolean bit) {
		
		bbyte.add(bit);
		
	}
	
	public static Binary xor(Binary bin1, Binary bin2) throws InputMismatchException {
		
		if (bin1.length() != bin2.length()) throw new InputMismatchException("The two binary objects were not the same length.");
		
		Binary output = new Binary();
		
		for (int bit = 0; bit < bin1.length(); bit++) {
			
			output.append(bin1.getBit(bit) ^ bin2.getBit(bit));
			
		}
		
		return output;
		
	}
	
	public static Binary xorOctet(Binary[] bin1, Binary[] bin2) throws InputMismatchException {
		
		if (bin1.length != bin2.length) throw new InputMismatchException("The two binary objects were not the same length.");
		
		Binary output = new Binary();
		
		for (int octet = 0; octet < bin1.length; octet++) {
			
			Binary binaryOctet = new Binary();
			
			for (int bit = 0; bit < bin1[octet].getBinary().size(); bit++) {
				
				binaryOctet.append(bin1[octet].getBit(bit) ^ bin2[octet].getBit(bit));
				
			}
			
			output.append(binaryOctet);
			
		}
		
		return output;
		
	}
	
	public boolean getBit(int index) {
		
		return bbyte.get(index);
		
	}
	
	public void putBit(int index, boolean bit) {
		
		bbyte.add(index, bit);
		
	}
	
	public int length() {
		
		return bbyte.size();
		
	}
	
	public ArrayList<Boolean> getBinary() {
		
		return bbyte;
		
	}
	
	public Boolean[] getBinaryArray() {
		
		return bbyte.toArray(new Boolean[0]);
		
	}
	
	public Binary[] getBinaryOctetArray() {
		
		ArrayList<Binary> octetList = new ArrayList<>();
		for (int i = 0; i < bbyte.size() / 8; i++) {
			
			octetList.add(new Binary(this, i * 8, (i * 8) + 8));
			
		}
		
		return octetList.toArray(new Binary[0]);
		
	}
	
	public String toString() {
		
		String output = "";
		
		for (boolean bit: bbyte) {
			
			if (bit) {
				
				output += "1";
				
			} else {
				
				output += "0";
				
			}
			
		}
		
		return output;
		
	}
	
	public String toString(boolean spaces) {
		
		StringBuilder output = new StringBuilder("");
		
		for (boolean bit: bbyte) {
			
			if (bit) {
				
				output.append("1");
				
			} else {
				
				output.append("0");
				
			}
			
		}
		
		if (spaces) {
			
			for (int i = output.length() - 4; i > 0; i -= 4) {
				
				output.insert(i, " ");
				
			}
			
		}
		
		return output.toString();
		
	}
	
	public String toStringFormatted(int format) {
		
		StringBuilder output = new StringBuilder("");
		
		for (boolean bit: bbyte) {
			
			if (bit) {
				
				output.append("1");
				
			} else {
				
				output.append("0");
				
			}
			
		}
		
		switch (format) {

			case 0: // FORMAT_NO_LEADING_ZEROS
				output = new StringBuilder(output.toString().replaceAll("^0+", ""));
				
				break;

			case 1: // FORMAT_NO_LEADING_ZEROS_WITH_SPACES
				output = new StringBuilder(output.toString().replaceAll("^0+", ""));
				
				for (int i5 = output.length() / 4; i5 != 0; i5--) {
					output.insert(output.length() - (i5 * 4), " ");
					
				}

				break;

			case 2: // FORMAT_FULL_BYTES
				for (int i4 = 8 - (output.length() % 8); i4 != 0; i4--) {
					if (i4 == 8) { break; }
					output.insert(0, "0");
					
				}
				
				break;

			case 3: // FORMAT_FULL_BYTES_WITH_SPACES
				for (int i4 = 8 - (output.length() % 8); i4 != 0; i4--) {
					if (i4 == 8) { break; }
					output.insert(0, "0");
					
				}
				
				for (int i5 = output.length() / 4; i5 != 0; i5--) {
					output.insert(output.length() - (i5 * 4), " ");
					
				}
				
				break;

			default:
				throw new MalformedParametersException();

		}
		
		return output.toString().trim();
		
	}
	
	public String toMultilineString() {
		
		Binary[] octets = getBinaryOctetArray();
		String output = "";
		
		for (int octet = 0; octet < octets.length; octet++) {
			
			output += octets[octet].toString();
			
			if (octet != octets.length - 1) {
				
				output += "\r\n";
				
			}
			
		}
		
		return output;
		
	}
	
}
