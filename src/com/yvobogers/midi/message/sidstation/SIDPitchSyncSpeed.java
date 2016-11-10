package com.yvobogers.midi.message.sidstation;

public class SIDPitchSyncSpeed extends SIDDirectProgram {
	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDPitchSyncSpeed(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00);
		add(0x22);
		add(0x7f);
		add(0x00);
		add(val);
		add(END);
	}
}
