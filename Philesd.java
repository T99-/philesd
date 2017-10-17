package com.t99sdevelopment.philesd;

import com.t99sdevelopment.philesd.cli.CLI;
import com.t99sdevelopment.philesd.exception.IncompleteStringException;
import com.t99sdevelopment.philesd.util.StringExtractor;

public class Philesd {
	
	public static void main(String[] args) {

		new Thread(new CLI()).start();

		try {

			System.out.println(StringExtractor.processNextString("\"Hey\" what's up")[1]);

		} catch (IncompleteStringException e) {



		}

	}
	
}