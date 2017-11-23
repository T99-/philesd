package io.t99.philesd.cli;

import java.util.Scanner;

public final class CLI implements Runnable {
	
	static Scanner inputStream = new Scanner(System.in);

	private static Context context;

	private static CLIStream baseStream = new CLIStream("base", Context.BASE);
	public static CLIStream focus = baseStream;
	
	public void run() {

		while(true) {

			//processCommand(inputStream.nextLine());

		}

	}
	
//  I guess I got rid of a couple of classes somewhere along the way? meh, not my concern rn -- TODO
//	public static void processCommand(String input) {
//
//		CommandResource commandResource = CommandInterpreter.interpret(input);
//
//		try {
//
//			CommandManager.execute(commandResource.getCommand());
//
//		} catch (CommandNotFoundException e) {
//
//			System.out.println("Could not find command: `" + commandResource.getCommand() + "`");
//
//		}
//
//	}
	
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

}