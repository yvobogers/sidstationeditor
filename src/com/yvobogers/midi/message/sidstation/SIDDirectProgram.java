package com.yvobogers.midi.message.sidstation;

import com.yvobogers.midi.util.Constants;

public abstract class SIDDirectProgram extends SIDMessage implements Constants {
	
	protected static final int COMMAND_ID = 0x04;
	
	public SIDDirectProgram(){
		super();
		add(COMMAND_ID);
	}
}
