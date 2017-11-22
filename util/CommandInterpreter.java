package com.t99sdevelopment.philesd.util;

import java.util.ArrayList;

public class CommandInterpreter {

	public static CommandResource interpret(String input) {
		
		CommandResource command = new CommandResource();

		String modifiedInput = input.trim();
		
		int indexOfFirstSpace = modifiedInput.indexOf(" ");
		
		command.setCommand(modifiedInput.substring(0, indexOfFirstSpace));
		
		modifiedInput = modifiedInput.substring(indexOfFirstSpace + 1);

		command.addFlagPairs(nextFlag(modifiedInput, new ArrayList<>()));
		
		return command;
		
	}

	public static FlagDataPair[] nextFlag(String input, ArrayList<FlagDataPair> pairs) {

		String modifiedInput = input.trim();
		String flag;
		String data;

		try {

			if (modifiedInput.length() != 0) {

				if (modifiedInput.substring(0,1).equals("-")) { // Checks if the first character of the string is a dash.

					if (modifiedInput.substring(1, modifiedInput.indexOf(" ")).length() > 1 || modifiedInput.substring(1, modifiedInput.indexOf(" ")).length() == 0) { // Checks if the string following the dash is a single character or not.

						// invalid flag - return error

					} else { // The character following the dash was singular.

						flag = modifiedInput.substring(1, 2);
						FlagDataPair[] recursionBuffer;

						if (modifiedInput.substring(3, 4).equals("-")) { // Checks if the next character is a dash.

							data = "";
							modifiedInput = modifiedInput.substring(3);
							pairs.add(new FlagDataPair(flag, data));

						} else if (modifiedInput.substring(3, 4).equals("'")) {

							data = modifiedInput.substring(4, modifiedInput.indexOf("'", 4));
							modifiedInput = modifiedInput.substring(modifiedInput.indexOf("'"), modifiedInput.indexOf("'"));
							pairs.add(new FlagDataPair(flag, data));

						} else if (modifiedInput.substring(3, 4).equals("\"")) {

							data = modifiedInput.substring(4, modifiedInput.indexOf("\"", 4));
							modifiedInput = modifiedInput.substring(modifiedInput.indexOf("\""), modifiedInput.indexOf("\""));
							pairs.add(new FlagDataPair(flag, data));

						} else {

							data = modifiedInput.substring(3, modifiedInput.indexOf(" ", 3));
							modifiedInput = modifiedInput.substring(modifiedInput.indexOf(" ", 3));
							pairs.add(new FlagDataPair(flag, data));

						}

						recursionBuffer = nextFlag(modifiedInput, pairs);

						return pairs.toArray(new FlagDataPair[pairs.size()]);

					}

				}

			} else {

				return null;

			}

		} catch (Exception e) {

			System.out.println("Malformed command.");

		}

		return null;

	}
	
}