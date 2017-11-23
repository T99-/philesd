package io.t99.philesd.exception;
//Created by T99 at 9:10 PM, October [DAY...], 2017.

public class IncompleteStringException extends Exception {

	public IncompleteStringException() {

		super();

	}

	public IncompleteStringException(String message) {

		super(message);

	}

	public IncompleteStringException (String message, Throwable cause) {

		super(message, cause);

	}

	public IncompleteStringException (Throwable cause) {

		super(cause);

	}

	protected IncompleteStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);

	}

}