package com.yvobogers.midi.message.sidstation;

public class SIDFilterEnvelopeAttack extends SIDDirectProgram {

	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterEnvelopeAttack(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		
		add(0x00); add(0x1c); add(0x7f); add(0x00); add(val); add(END);
	}
}
