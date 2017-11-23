package io.t99.philesd.util;

import io.t99.philesd.exception.IncompleteStringException;

import java.util.ArrayList;

public class StringExtractor {

//	public static String[] getStringAsWordArray(String input) {
//
//		ArrayList<String> array = new ArrayList<>();
//
//		while (!input.equals("")) {
//
//			String[] results;
//
//			try {
//
//				results = processNextString(input);
//
//			} catch (IncompleteStringException e) {
//
//				// you should really be catching this further down stream
//
//			}
//
//			if (results != null) {
//
//
//
//			}
//
//			// input = TODO
//
//		}
//
//	}

	public static String[] processNextString(String input) throws IncompleteStringException {

		input = input.trim();
		String firstCharacter;

		try {

			firstCharacter = input.substring(0, 1); // Contains the first character.

		} catch (IndexOutOfBoundsException e) {

			return new String[] {""};

		}

		if (firstCharacter.equals("\"")) { // Checks if the first character is a ".

			if (input.substring(1).contains("\"")) { // Checks if the remainder of the string contains another ".

				return new String[] {input.substring(1, input.substring(1).indexOf("\"") + 1), input.substring(input.substring(1).indexOf("\"") + 2)};

			} else {

				throw new IncompleteStringException();

			}

		} else if (firstCharacter.equals("'")) { // Checks if the first character is a '.

			if (input.substring(1).contains("'")) { // Checks if the remainder of the string contains another '.

				return new String[]{input.substring(1, input.substring(1).indexOf("'") + 1), input.substring(input.substring(1).indexOf("'") + 2)};

			} else {

				throw new IncompleteStringException();

			}

		} else {

			if (input.contains(" ")) {

				return new String[] {input.substring(0, input.indexOf(" ")), input.substring(input.indexOf(" "))};

			} else {

				return new String[] {input};

			}

		}

	}

}