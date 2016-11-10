package com.yvobogers.midi.message.sidstation;

public class SIDFilterLfoDepth extends SIDDirectProgram {
	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterLfoDepth(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00);
		add(0x20);
		add(0x7f);
		add(0x00);
		add(val);
		add(END);
	}
}
