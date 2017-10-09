package com.t99sdevelopment.philesd.cli;

import java.util.Scanner;

public final class CLI implements Runnable {
	
	Scanner inputStream = new Scanner(System.in);

	private static int context;
	public static final int BASE_CONTEXT = 0;
	public static final int CLISTREAM_CONTEXT = 1;

	private static CLIStream baseStream = new CLIStream("base", BASE_CONTEXT);
	public static CLIStream focus = baseStream;
	
	public static void input() {
	

	
	}
	
	public static void clearConsole()	{
		
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

	public static void setContext(int newContext) {

		context = newContext;

	}

	public static int getContext() {

		return context;

	}

	@Override
	public void run() {



	}

}