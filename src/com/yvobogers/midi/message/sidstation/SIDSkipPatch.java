package com.yvobogers.midi.message.sidstation;

public class SIDSkipPatch extends SIDMessage {
	
	private static final byte COMMAND_ID = (byte)0x03;
	
	public SIDSkipPatch(){
		super();
		add(COMMAND_ID);
		add(END);
	}
}
