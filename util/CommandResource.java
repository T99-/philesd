package com.t99sdevelopment.philesd.util;

import java.util.HashMap;

public class CommandResource {
	
	private String command;
	private HashMap<String, String> flagPairs = new HashMap<>();
	
	public CommandResource() {} // TODO - Change constructors back to protected
	
	public CommandResource (String command) {
		
		this.command = command;
		
	}
	
	public CommandResource (String command, FlagDataPair... pairs) {
		
		this.command = command;
		
		for (FlagDataPair pair: pairs) {
			
			flagPairs.put(pair.flag, pair.data);
			
		}
		
	}
	
	public void setCommand(String command) {
		
		this.command = command;
		
	}
	
	public String getCommand() {
		
		return command;
		
	}
	
	public void addFlagPairs(FlagDataPair... pairs) {
		
		try {

			for (FlagDataPair pair: pairs) {

				if (pair.data != null) {

					flagPairs.put(pair.flag, pair.data);

				} else {

					flagPairs.put(pair.flag, "");

				}


			}

		} catch (Exception e) {

			System.out.println("Malformed command.");

		}
		
	}
	
	public FlagDataPair[] getFlagDataPairs() {
		
		return (FlagDataPair[]) flagPairs.entrySet().toArray();
		
	}
	
	public String getDataForFlag(String flag) {
		
		if (flagPairs.containsKey(flag)) {
			
			return flagPairs.get(flag);
			
		} else {
			
			return null;
			
		}
		
	}

	public void debugOutput() {

		System.out.println("Command:\t" + command);

		int flagNumber = 1;

		for (String flag: flagPairs.keySet()) {

			System.out.println("Flag #" + flagNumber + ":\t" + flag);

			if (flagPairs.get(flag) != "") {

				System.out.println("Data #" + flagNumber + ":\t" + flagPairs.get(flag));

			}

			flagNumber++;

		}

	}

}