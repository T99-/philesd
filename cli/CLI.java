package com.t99sdevelopment.philesd.cli;

import com.t99sdevelopment.philesd.exception.CommandNotFoundException;
import com.t99sdevelopment.philesd.util.CommandInterpreter;
import com.t99sdevelopment.philesd.util.CommandResource;

import java.util.Scanner;

public final class CLI implements Runnable {
	
	static Scanner inputStream = new Scanner(System.in);

	private static int context;
	public static final int BASE_CONTEXT = 0;
	public static final int CLISTREAM_CONTEXT = 1;

	private static CLIStream baseStream = new CLIStream("base", BASE_CONTEXT);
	public static CLIStream focus = baseStream;
	
	public void run() {

		while(true) {

			processCommand(inputStream.nextLine());

		}

	}

	public static void processCommand(String input) {

		CommandResource commandResource = CommandInterpreter.interpret(input);

		try {

			CommandManager.execute(commandResource.getCommand());

		} catch (CommandNotFoundException e) {

			System.out.println("Could not find command: `" + commandResource.getCommand() + "`");

		}

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

}