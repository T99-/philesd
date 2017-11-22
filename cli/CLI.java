package com.t99sdevelopment.philesd.cli;

import java.util.Scanner;

public final class CLI implements Runnable {
	
	Scanner inputStream = new Scanner(System.in);

	private static Context context;

	private static CLIStream baseStream = new CLIStream("base", Context.BASE);
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

	public static void setContext(Context newContext) {

		context = newContext;

	}

	public static Context getContext() {

		return context;

	}

	@Override
	public void run() {



	}

}