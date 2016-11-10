package com.yvobogers.midi.message.sidstation;

public class SIDFilterEnvelopeSustain extends SIDDirectProgram {

	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterEnvelopeSustain(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00);
		add(0x1e);
		add(0x7f);
		add(0x00);
		add(val);
		add(END);
	}
}
