package com.yvobogers.midi.message.sidstation;

public class SIDFilterCutoff extends SIDDirectProgram {

	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterCutoff(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		add(0x00); add(0x1a); add(0x7f); add(0x00); add(val); add(END);
	}
}
