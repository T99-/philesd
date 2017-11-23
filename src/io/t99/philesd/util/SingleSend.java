package io.t99.philesd.util;

import io.t99.philesd.cli.CLIStream;

public class SingleSend {
	
	public static String lastSent = "";
	
	public static void print(String string) {

		if (!string.equals(lastSent)) {

			System.out.print(string);
			lastSent = string;

		}

	}

	public static void println(String string) {

		if (!string.equals(lastSent)) {

			System.out.println(string);
			lastSent = string;

		}

	}

	public static void printToStream(String string, CLIStream stream) {

		if (!string.equals(lastSent)) {

			stream.print(string);
			lastSent = string;

		}

	}

	public static void printlnToStream(String string, CLIStream stream) {

		if (!string.equals(lastSent)) {

			stream.println(string);
			lastSent = string;

		}

	}
	
}