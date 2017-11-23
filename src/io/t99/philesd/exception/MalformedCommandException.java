package com.t99sdevelopment.philesd.exception;
//Created by T99 at 7:09 PM, October [DAY...], 2017.

public class MalformedCommandException extends Exception {

	public MalformedCommandException() {

		super();

	}

	public MalformedCommandException(String message) {

		super(message);

	}

	public MalformedCommandException(String message, Throwable cause) {

		super(message, cause);

	}

	public MalformedCommandException(Throwable cause) {

		super(cause);

	}

	protected MalformedCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);

	}

}