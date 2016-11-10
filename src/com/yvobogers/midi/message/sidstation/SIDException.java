package com.yvobogers.midi.message.sidstation;

import com.yvobogers.midi.message.MessageException;

@SuppressWarnings("serial")
public class SIDException extends MessageException {

	public SIDException() {
		super();
	}

	public SIDException(String message, Throwable cause) {
		super(message, cause);
	}

	public SIDException(String message) {
		super(message);
	}

	public SIDException(Throwable cause) {
		super(cause);
	}
}
