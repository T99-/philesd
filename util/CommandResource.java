package com.t99sdevelopment.philesd.util;

import java.util.HashMap;

public class CommandResource {
	
	private String command;
	private HashMap<String, String> flagPairs;
	
	protected CommandResource() {}
	
	protected CommandResource (String command) {
		
		this.command = command;
		
	}
	
	protected CommandResource (String command, FlagDataPair... pairs) {
		
		this.command = command;
		
		for (FlagDataPair pair: pairs) {
			
			flagPairs.put(pair.primary, pair.secondary);
			
		}
		
	}
	
	public void setCommand(String command) {
		
		this.command = command;
		
	}
	
	public String getCommand() {
		
		return command;
		
	}
	
	public void addFlagPairs(FlagDataPair... pairs) {
		
		for (FlagDataPair pair: pairs) {
			
			flagPairs.put(pair.primary, pair.secondary);
			
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
	
}