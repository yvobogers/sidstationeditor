package com.yvobogers.midi.message;

import com.yvobogers.midi.message.sidstation.SIDDirectProgramFactory;


public abstract class AbstractMessageFactory {
	
	protected static AbstractMessageFactory theirInstance = null;

	public static AbstractMessageFactory getFactory(){
		// could add factories for building patches instead of direct
		// programs here, or even factories for non-SID devices 
		return SIDDirectProgramFactory.instance();
	}
	
	public abstract AbstractMessage buildMessage(String aFunction, Object aValue)
	throws MessageException;
	
}
