package com.yvobogers.midi.message.sidstation;

public class SIDFilterLfoWheelDepth extends SIDDirectProgram {
	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterLfoWheelDepth(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00);
		add(0x21);
		add(0x7f);
		add(0x00);
		add(val);
		add(END);
	}
}
