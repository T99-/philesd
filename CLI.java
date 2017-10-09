package com.t99sdevelopment.philesd;

import java.util.Scanner;

public final class CLI {

	private CLI() {
		
		// nothing
		
	}
	
	Scanner inputStream = new Scanner(System.in);
	
	public static void input() {
	
	
	
	}
	
	public final static void clearConsole()	{
		
		try {
			
			final String os = System.getProperty("os.name");
			
			if (os.contains("Windows")) {
				
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			
			}
			
			else {
				
				Runtime.getRuntime().exec("clear");
				
			}
			
		} catch (final Exception e) {
			
			//  Handle any exceptions.
			
		}
		
	}

}