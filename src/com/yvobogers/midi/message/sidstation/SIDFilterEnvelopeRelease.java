package com.yvobogers.midi.message.sidstation;

public class SIDFilterEnvelopeRelease extends SIDDirectProgram {
	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterEnvelopeRelease(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00);
		add(0x1f);
		add(0x7f);
		add(0x00);
		add(val);
		add(END);
	}
}
