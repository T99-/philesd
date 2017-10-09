package com.t99sdevelopment.philesd.util;

import java.util.ArrayList;

public class CommandInterpreter {
	
	public static CommandResource interpret(String input) {
		
		CommandResource command = new CommandResource();
		
		String modifiedInput = input.trim();
		
		int indexOfFirstSpace = modifiedInput.indexOf(" ");
		
		command.setCommand(modifiedInput.substring(0, indexOfFirstSpace));
		
		System.out.println(command.getCommand());
		
		modifiedInput = modifiedInput.substring(indexOfFirstSpace + 1);
		
		System.out.println(modifiedInput);
		
		return command;
		
	}
	
	public static FlagDataPair[] nextFlag(String input) {
		
		ArrayList<FlagDataPair> pairs = new ArrayList<>();
		
		if (input.contains("-")) {
		
		
		
		}
		
	}
	
}