package com.yvobogers.midi.message.sidstation;

public class SIDPitchSyncHCut extends SIDDirectProgram {
	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDPitchSyncHCut(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00);
		add(0x23);
		add(0x0f);
		add(0x00);
		add(val);
		add(END);
	}
}
